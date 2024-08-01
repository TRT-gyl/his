<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="姓名" prop="doctName">
        <el-input v-model="queryParams.doctName" placeholder="请输入姓名" clearable @keyup.enter.native="handleQuery" />
      </el-form-item>
      <el-form-item label="所属科室" prop="deptId">
        <el-input v-model="queryParams.deptId" placeholder="请输入所属部门" clearable @keyup.enter.native="handleQuery" />
      </el-form-item>
      <el-form-item label="手机号" prop="doctPhone">
        <el-input v-model="queryParams.doctPhone" placeholder="请输入手机号" clearable @keyup.enter.native="handleQuery" />
      </el-form-item>
      <el-form-item>
        <el-button type="primary" icon="el-icon-search" size="mini" @click="handleQuery">搜索</el-button>
        <el-button icon="el-icon-refresh" size="mini" @click="resetQuery">重置</el-button>
      </el-form-item>
    </el-form>

    <el-row :gutter="10" class="mb8">
      <el-col :span="1.5">
        <el-button type="primary" plain icon="el-icon-plus" size="mini" @click="handleAdd"
          v-hasPermi="['his:doctor:add']">新增
        </el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button type="success" plain icon="el-icon-edit" size="mini" :disabled="single" @click="handleUpdate"
          v-hasPermi="['his:doctor:edit']">修改
        </el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button type="danger" plain icon="el-icon-delete" size="mini" :disabled="multiple" @click="handleDelete"
          v-hasPermi="['his:doctor:remove']">删除
        </el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['his:doctor:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="doctorList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="医生编号" align="center" prop="doctId" />
      <el-table-column label="姓名" align="center" prop="doctName" />
      <el-table-column label="所属部门" align="center" prop="dept.deptName" />
      <el-table-column label="手机号" align="center" prop="doctPhone" />
      <el-table-column label="性别" align="center" prop="doctSex">
        <template slot-scope="scope">
          <dict-tag :options="dict.type.his_doctor_sex" :value="scope.row.doctSex" />
        </template>
      </el-table-column>
      <el-table-column label="年龄" align="center" prop="doctAge" />
      <el-table-column label="医生职称" align="center" prop="doctLevel">
        <template slot-scope="scope">
          <dict-tag :options="dict.type.his_docter_level" :value="scope.row.doctLevel" />
        </template>
      </el-table-column>
      <el-table-column label="是否排班" align="center" prop="isOrderwork">
        <template slot-scope="scope">
          <dict-tag :options="dict.type.his_is_orderwork" :value="scope.row.isOrderwork" />
        </template>
      </el-table-column>
      <el-table-column label="学历背景" align="center" prop="doctEducationBg">
        <template slot-scope="scope">
          <dict-tag :options="dict.type.his_docter_bg" :value="scope.row.doctEducationBg" />
        </template>
      </el-table-column>
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button size="mini" type="text" icon="el-icon-edit" @click="handleUpdate(scope.row)"
            v-hasPermi="['his:doctor:edit']">修改
          </el-button>
          <el-button size="mini" type="text" icon="el-icon-delete" @click="handleDelete(scope.row)"
            v-hasPermi="['his:doctor:remove']">删除
          </el-button>
        </template>
      </el-table-column>
    </el-table>

    <pagination v-show="total > 0" :total="total" :page.sync="queryParams.pageNum" :limit.sync="queryParams.pageSize"
      @pagination="getList" />

    <!-- 添加或修改医生对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="姓名" prop="doctName">
          <el-input v-model="form.doctName" placeholder="请输入姓名" />
        </el-form-item>

            <el-form-item label="所属科室">
              <el-select v-model="form.deptId" placeholder="请选择科室">
                <el-option
                  v-for="item in deptOptions"
                  :key="item.deptId"
                  :label="item.deptName"
                  :value="item.deptId"
                  :disabled="item.status == 1"
                ></el-option>
              </el-select>
            </el-form-item>
    
        <el-form-item label="手机号" prop="doctPhone">
          <el-input v-model="form.doctPhone" placeholder="请输入手机号" />
        </el-form-item>
        <el-form-item label="性别" prop="doctSex">
          <el-select v-model="form.doctSex" placeholder="请选择性别">
            <el-option v-for="dict in dict.type.his_doctor_sex" :key="dict.value" :label="dict.label"
              :value="dict.value"></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="年龄" prop="doctAge">
          <el-input v-model="form.doctAge" placeholder="请输入年龄" />
        </el-form-item>
        <el-form-item label="医生职称" prop="doctLevel">
          <el-select v-model="form.doctLevel" placeholder="请选择医生职称">
            <el-option v-for="dict in dict.type.his_docter_level" :key="dict.value" :label="dict.label"
              :value="dict.value"></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="是否排班" prop="isOrderwork">
          <el-select v-model="form.isOrderwork" placeholder="请选择是否排班">
            <el-option v-for="dict in dict.type.his_is_orderwork" :key="dict.value" :label="dict.label"
              :value="dict.value"></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="学历背景" prop="doctEducationBg">
          <el-select v-model="form.doctEducationBg" placeholder="请选择学历背景">
            <el-option v-for="dict in dict.type.his_docter_bg" :key="dict.value" :label="dict.label"
              :value="dict.value"></el-option>
          </el-select>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="submitForm">确 定</el-button>
        <el-button @click="cancel">取 消</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
import { addDoctor, delDoctor, getDoctor, listDoctor, updateDoctor } from "@/api/his/doctor";
import { listDept} from "@/api/his/dept";
export default {
  name: "Doctor",
  dicts: ['his_docter_bg', 'his_is_orderwork', 'his_docter_level', 'his_doctor_sex'],
  data() {
    return {
      // 遮罩层
      loading: true,
      // 选中数组
      ids: [],
      // 非单个禁用
      single: true,
      // 非多个禁用
      multiple: true,
      // 显示搜索条件
      showSearch: true,
      // 总条数
      total: 0,
      // 医生表格数据
      doctorList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        doctName: null,
        deptId: null,
        doctPhone: null,
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
        doctPassword: [
          { required: true, message: "密码不能为空", trigger: "blur" }
        ],
        doctName: [
          { required: true, message: "姓名不能为空", trigger: "blur" }
        ],
      }
    };
  },
  created() {
    this.getList();
  },
  methods: {
    /** 查询医生列表 */
    getList() {
      this.loading = true;
      listDoctor(this.queryParams).then(response => {
        this.doctorList = response.rows;
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
        doctId: null,
        doctPassword: null,
        doctName: null,
        deptId: null,
        doctPhone: null,
        doctSex: null,
        doctAge: null,
        doctLevel: null,
        isOrderwork: null,
        doctEducationBg: null,
        createBy: null,
        createTime: null,
        updateBy: null,
        updateTime: null,
         // 科室选项
        deptOptions: [],
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
      this.handleQuery();
    },
    // 多选框选中数据
    handleSelectionChange(selection) {
      this.ids = selection.map(item => item.doctId)
      this.single = selection.length !== 1
      this.multiple = !selection.length
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset();
      listDept().then(response => {
        this.deptOptions = response.rows;
        this.open = true;
        this.title = "添加医生";
      });
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const doctId = row.doctId || this.ids
      getDoctor(doctId).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改医生";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.doctId != null) {
            updateDoctor(this.form).then(response => {
              this.$modal.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addDoctor(this.form).then(response => {
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
      const doctIds = row.doctId || this.ids;
      this.$modal.confirm('是否确认删除医生编号为"' + doctIds + '"的数据项？').then(function () {
        return delDoctor(doctIds);
      }).then(() => {
        this.getList();
        this.$modal.msgSuccess("删除成功");
      }).catch(() => {
      });
    },
     /** 导出按钮操作 */
     handleExport() {
      this.download('his/doctor/export', {
        ...this.queryParams
      }, `doctor_${new Date().getTime()}.xlsx`)
    }  
  }
};
</script>
