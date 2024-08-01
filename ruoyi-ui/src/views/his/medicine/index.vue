<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="药品名称" prop="medicineName">
        <el-input
          v-model="queryParams.medicineName"
          placeholder="请输入药品名称"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="关键字" prop="keyword">
        <el-input
          v-model="queryParams.keyword"
          placeholder="请输入关键字"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>

      <el-form-item label="药品类型" prop="status">
        <el-select v-model="queryParams.medicineType" placeholder="药品类型" clearable>
          <el-option
            v-for="dict in dict.type.his_medicine_type"
            :key="dict.value"
            :label="dict.label"
            :value="dict.value"
          />
        </el-select>
      </el-form-item>

      <el-form-item label="生产厂家" props="factoryId">
        <el-select v-model="queryParams.factoryId" placeholder="请选择生产厂家">
          <el-option
            v-for="item in factoryOptions"
            :key="item.factoryId"
            :label="item.factoryName"
            :value="item.factoryId"
            :disabled="item.status == 1"
          ></el-option>
        </el-select>
      </el-form-item>


      <el-form-item label="处方类型" prop="status">
        <el-select v-model="queryParams.prescribe" placeholder="处方类型" clearable>
          <el-option
            v-for="dict in dict.type.his_medicine_prescribe"
            :key="dict.value"
            :label="dict.label"
            :value="dict.value"
          />
        </el-select>
      </el-form-item>

      <el-form-item label="状态" prop="status">
        <el-select v-model="queryParams.status" placeholder="可用状态" clearable>
          <el-option
            v-for="dict in dict.type.sys_normal_disable"
            :key="dict.value"
            :label="dict.label"
            :value="dict.value"
          />
        </el-select>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" icon="el-icon-search" size="mini" @click="handleQuery">搜索</el-button>
        <el-button icon="el-icon-refresh" size="mini" @click="resetQuery">重置</el-button>
      </el-form-item>
    </el-form>

    <el-row :gutter="10" class="mb8">
      <el-col :span="1.5">
        <el-button
          type="primary"
          plain
          icon="el-icon-plus"
          size="mini"
          @click="handleAdd"
          v-hasPermi="['his:medicine:add']"
        >新增</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="success"
          plain
          icon="el-icon-edit"
          size="mini"
          :disabled="single"
          @click="handleUpdate"
          v-hasPermi="['his:medicine:edit']"
        >修改</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="danger"
          plain
          icon="el-icon-delete"
          size="mini"
          :disabled="multiple"
          @click="handleDelete"
          v-hasPermi="['his:medicine:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['his:medicine:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="medicineList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="药品ID" align="center" prop="medicineId" />
      <el-table-column label="药品名称" align="center" prop="medicineName" />
      <el-table-column label="药品编码" align="center" prop="medicineNo" />
      <el-table-column label="生产厂家" align="center" prop="factory.factoryName" />
      <el-table-column label="药品类型" align="center" prop="medicineType" />
      <el-table-column label="处方类型" align="center" prop="prescribe" />
      <el-table-column label="单位" align="center" prop="unit" />
      <el-table-column label="处方价格" align="center" prop="price" />
      <el-table-column label="库存量" align="center" prop="stock" />
      <el-table-column label="预警值" align="center" prop="warn" />
      <el-table-column label="换算量" align="center" prop="conversion" />

      <el-table-column label="状态" align="center" prop="status">
        <template slot-scope="scope">
          <dict-tag :options="dict.type.sys_normal_disable" :value="scope.row.status" />
        </template>
      </el-table-column>
      <el-table-column label="创建时间" align="center" prop="createTime" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.createTime) }}</span>
        </template>
      </el-table-column>
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['his:medicine:edit']"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['his:medicine:remove']"
          >删除</el-button>
        </template>
      </el-table-column>
    </el-table>

    <pagination
      v-show="total>0"
      :total="total"
      :page.sync="queryParams.pageNum"
      :limit.sync="queryParams.pageSize"
      @pagination="getList"
    />

    <!-- 添加或修改药品对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="800px"  append-to-body >
      <el-row>
        <el-col :span="24">
          <el-form label-position="labelPosition"  ref="form" :model="form" :rules="rules" label-width="80px"  >
              <el-row>
                <el-col :span="12">
                  <el-form-item label="药品名称" prop="medicineName">
                    <el-input v-model="form.medicineName" placeholder="请输入药品名称" />
                    </el-form-item>
                </el-col>

                <el-col :span="12">
                  <el-form-item label="药品编码" prop="medicineNo">
                    <el-input v-model="form.medicineNo" placeholder="请输入药品编码名称" />
                  </el-form-item>
                </el-col>
                <el-col :span="12">
                   <el-form-item label="关键字" prop="keyword">
                   <el-input v-model="form.keyword"  placeholder="请输入关键字"/>
                     </el-form-item>
                </el-col>

                <el-col :span="12">
                  <el-form-item label="单位" prop="unit">
                    <el-input v-model="form.unit"  placeholder="请输入单位" />
                  </el-form-item>
                </el-col>

                <el-col :span="12">
                  <el-form-item label="换算量" prop="conversion">
                    <el-input v-model="form.conversion"/>
                  </el-form-item>
                </el-col>


              <el-col :span="12">
                <el-form-item label="处方价格" prop="price">
                  <el-input-number v-model="form.price"  :min="1" />
                </el-form-item>
              </el-col>
              <el-col :span="12">
                <el-form-item label="库存量" prop="stock">
                  <el-input v-model="form.stock"  />
                </el-form-item>
              </el-col>

                <el-col :span="12">
                  <el-form-item label="预警值" prop="price">
                    <el-input-number v-model="form.warn" controls-position="right" :min="1" />
                  </el-form-item>
                </el-col>

                <el-col :span="12">
                  <el-form-item label="状态" prop="status">
                    <el-select v-model="form.status" placeholder="请选择状态">
                      <el-option v-for="dict in dict.type.sys_normal_disable" :key="dict.value" :label="dict.label"
                                           :value="dict.value"></el-option>
                    </el-select>
                  </el-form-item>
                </el-col>

                <el-col :span="12">
                  <el-form-item label="药品类型" prop="medicineType">
                    <el-select v-model="form.medicineType" placeholder="请选择药品类型">
                      <el-option v-for="dict in dict.type.his_medicine_type" :key="dict.value" :label="dict.label"
                                 :value="dict.value"></el-option>
                    </el-select>
                  </el-form-item>
                </el-col>

                <el-col :span="12">
                  <el-form-item label="处方类型" prop="medicineType">
                    <el-select v-model="form.prescribe" placeholder="请选择处方类型">
                      <el-option v-for="dict in dict.type.his_medicine_type" :key="dict.value" :label="dict.label"
                                 :value="dict.value"></el-option>
                    </el-select>
                  </el-form-item>
                </el-col>

                <el-col :span="12">
                  <el-form-item label="生产厂家" props="factoryId">
                    <el-select v-model="form.factoryId" placeholder="请选择生产厂家">
                      <el-option
                        v-for="item in factoryOptions"
                        :key="item.factoryId"
                        :label="item.factoryName"
                        :value="item.factoryId"
                        :disabled="item.status == 1"
                      ></el-option>
                    </el-select>
                  </el-form-item>
                </el-col>

              </el-row>
          </el-form>
        </el-col>
      </el-row>
<!--      <el-form  ref="form" :model="form" :rules="rules" label-width="80px"  >-->
<!--        <el-form-item label="药品名称" prop="medicineName">-->
<!--          <el-input v-model="form.medicineName" placeholder="请输入药品名称" />-->
<!--        </el-form-item>-->
<!--        <el-form-item label="药品编码" prop="medicineNo">-->
<!--          <el-input v-model="form.medicineNo" placeholder="请输入编码名称" />-->
<!--        </el-form-item>-->


<!--        <el-form-item label="关键字" prop="keyword">-->
<!--          <el-input v-model="form.keyword" controls-position="right" placeholder="请输入关键字"/>-->
<!--        </el-form-item>-->

<!--        <el-form-item label="单位" prop="unit">-->
<!--          <el-input v-model="form.unit" controls-position="right" placeholder="请输入单位" />-->
<!--        </el-form-item>-->

<!--        <el-form-item label="换算量" prop="conversion">-->
<!--          <el-input v-model="form.conversion" controls-position="right"/>-->
<!--        </el-form-item>-->

<!--        <el-form-item label="处方价格" prop="price">-->
<!--          <el-input-number v-model="form.price" controls-position="right" :min="1" />-->
<!--        </el-form-item>-->

<!--        <el-form-item label="库存量" prop="stock">-->
<!--          <el-input v-model="form.stock" controls-position="right" />-->
<!--        </el-form-item>-->

<!--        <el-form-item label="预警值" prop="price">-->
<!--          <el-input-number v-model="form.warn" controls-position="right" :min="1" />-->
<!--        </el-form-item>-->

<!--        <el-form-item label="状态" prop="status">-->
<!--          <el-select v-model="form.status" placeholder="请选择状态">-->
<!--            <el-option v-for="dict in dict.type.sys_normal_disable" :key="dict.value" :label="dict.label"-->
<!--                       :value="dict.value"></el-option>-->
<!--          </el-select>-->
<!--        </el-form-item>-->

<!--        <el-form-item label="药品类型" prop="medicineType">-->
<!--          <el-select v-model="form.medicineType" placeholder="请选择药品类型">-->
<!--            <el-option v-for="dict in dict.type.his_medicine_type" :key="dict.value" :label="dict.label"-->
<!--                       :value="dict.value"></el-option>-->
<!--          </el-select>-->
<!--        </el-form-item>-->

<!--        <el-form-item label="处方类型" prop="medicineType">-->
<!--          <el-select v-model="form.prescribe" placeholder="请选择处方类型">-->
<!--            <el-option v-for="dict in dict.type.his_medicine_type" :key="dict.value" :label="dict.label"-->
<!--                       :value="dict.value"></el-option>-->
<!--          </el-select>-->
<!--        </el-form-item>-->

<!--        <el-form-item label="生产厂家" props="factoryId">-->
<!--          <el-select v-model="form.factoryId" placeholder="请选择生产厂家">-->
<!--            <el-option-->
<!--              v-for="item in factoryOptions"-->
<!--              :key="item.factoryId"-->
<!--              :label="item.factoryName"-->
<!--              :value="item.factoryId"-->
<!--              :disabled="item.status == 1"-->
<!--            ></el-option>-->
<!--          </el-select>-->
<!--        </el-form-item>-->

<!--        &lt;!&ndash;        <el-form-item label="备注" prop="remark">&ndash;&gt;-->
<!--        &lt;!&ndash;          <el-input v-model="form.remark" type="textarea" placeholder="请输入内容" />&ndash;&gt;-->
<!--        &lt;!&ndash;        </el-form-item>&ndash;&gt;-->
<!--      </el-form>-->
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="submitForm">确 定</el-button>
        <el-button @click="cancel">取 消</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
import { listMedicine, getMedicine, delMedicine, addMedicine, updateMedicine } from "@/api/his/medicine";
import {listFactory} from '@/api/his/factory';

export default {
  name: "Medicine",
  dicts: ['sys_normal_disable','his_medicine_type','his_medicine_prescribe'],
  data() {
    return {
      sign:0,//增加或者修改的标识
      // 遮罩层
      loading: true,
      // 选中数组
      ids: [],
      // 非单个禁用
      single: true,
      // 非多个禁用
      multiple: false,
      // 显示搜索条件
      showSearch: true,
      // 总条数
      total: 0,
      // 药品表格数据
      medicineList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,

      //存储厂家
      factoryOptions:[],

      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        medicineName:null,
        medicineType:null,
        keyword:null,
        prescribe:null,
        factoryId:null,
        status: null,
      },
      // 表单参数
      form: {

      },
      // 表单校验
      rules: {
        medicineName: [
          { required: true, message: "药品名称不能为空", trigger: "blur" }
        ],
        medicineNo: [
          { required: true, message: "药品编码不能为空", trigger: "blur" }
        ],

        keyword: [
          { required: true, message: "查询关键字不能为空", trigger: "blur" }
        ],





      }
    };
  },
  created() {
    this.getList();
  },
  methods: {
    /** 查询药品列表 */
    getList() {
      this.loading = true;

      listFactory().then(response=>{
        this.factoryOptions=response.rows;
      })

      listMedicine(this.queryParams).then(response => {
        this.medicineList = response.rows;
        this.total = response.total;
        this.loading = false;
      });
    },


    // 取消按钮
    cancel() {
      this.open = false;
      this.reset();
    },
    // 表单重置
    reset() {
      this.form = {
        medicineName: undefined,
        medicineNo: undefined,
        keyword:undefined,
        unit:undefined,
        factoryId: undefined,
        // factory:undefined,
        medicineType: "中草药",
        prescribe: "中药处方",
        price:1,
        stock:0,
        warn:1,
        conversion:1,
        status: "0",

      };
      this.resetForm("form");
    },
    /** 搜索按钮操作 */
    handleQuery() {
      this.queryParams.pageNum = 1;
      this.getList();
    },
    /** 重置按钮操作 */
    resetQuery() {
      this.resetForm("queryForm");

      this.queryParams.factoryId=null;
      this.queryParams.medicineType=null;
      this.queryParams.prescribe=null;

      this.handleQuery();
    },
    // 多选框选中数据
    handleSelectionChange(selection) {
      this.ids = selection.map(item => item.medicineId)
      this.single = selection.length!=1
      this.multiple = !selection.length
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.sign=1;
      this.reset();
      this.open = true;
      this.title = "添加药品";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.sign=2;
      this.reset();
      // this.form = row; //少查一次数据库
      // this.open = true;
      // this.title = "修改药品";


      const medicineId = row.medicineId || this.ids
      getMedicine(medicineId).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改药品";
      });
    },
    /** 提交按钮 */
    submitForm: function() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.sign==2) {
            updateMedicine(this.form).then(response => {
              this.$modal.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else if(this.sign==1) {
            addMedicine(this.form).then(response => {
              this.$modal.msgSuccess("新增成功");
              this.open = false;
              this.getList();
            });
          }
        }
      });
    },
    /** 删除按钮操作 */
    handleDelete(row) {
      const medicineIds = row.medicineId || this.ids;
      this.$modal.confirm('是否确认删除药品编号为"' + medicineIds + '"的数据项？').then(function() {
        return delMedicine(medicineIds);
      }).then(() => {
        this.getList();
        this.$modal.msgSuccess("删除成功");
      }).catch(() => {});
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('his/medicine/export', {
        ...this.queryParams
      }, `medicine_${new Date().getTime()}.xlsx`)
    }
  }
};
</script>
