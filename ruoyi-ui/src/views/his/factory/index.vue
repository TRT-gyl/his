<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="厂家名称" prop="factoryName">
        <el-input
          v-model="queryParams.factoryName"
          placeholder="请输入厂家名称"
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

      <el-form-item label="厂家电话" prop="keyword">
        <el-input
          v-model="queryParams.mobile"
          placeholder="请输入厂家电话"
          clearable
          @keyup.enter.native="handleQuery"
        />
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
          v-hasPermi="['his:factory:add']"
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
          v-hasPermi="['his:factory:edit']"
        >修改</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="danger"
          plain
          icon="el-icon-delete"
          size="mini"
          :disabled="multiple_del"
          @click="handleDelete"
          v-hasPermi="['his:factory:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['his:factory:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="factoryList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="厂家ID" align="center" prop="factoryId" />
      <el-table-column label="厂家名称" align="center" prop="factoryName" />
      <el-table-column label="厂家编码" align="center" prop="factoryNo" />
      <el-table-column label="联系人" align="center" prop="linkMan" />
      <el-table-column label="电话" align="center" prop="mobile" />
      <el-table-column label="关键字" align="center" prop="keyword" />
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
            v-hasPermi="['his:factory:edit']">
<!--            v-hasPermi="['his:factory:edit']"-->
          修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['his:factory:remove']"
          >
<!--            v-hasPermi="['his:factory:remove']"-->
          删除</el-button>
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

    <!-- 添加或修改岗位对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form :inlime="true" ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="厂家名称" prop="factoryName">
          <el-input v-model="form.factoryName" placeholder="请输入岗位名称" />
        </el-form-item>
        <el-form-item label="厂家编码" prop="factoryNo">
          <el-input v-model="form.factoryNo" placeholder="请输入编码名称" />
        </el-form-item>
        <el-form-item label="联系人" prop="linkMan">
          <el-input v-model="form.linkMan" controls-position="right" placeholder="请输入联系人" />
        </el-form-item>

        <el-form-item label="关键字" prop="keyword">
          <el-input v-model="form.keyword" controls-position="right" placeholder="请输入关键字" />
        </el-form-item>

        <el-form-item label="电话" prop="mobile">
          <el-input v-model="form.mobile" controls-position="right" placeholder="请输入电话" />
        </el-form-item>

        <el-form-item label="地址" prop="address">
          <el-input v-model="form.address" controls-position="right" placeholder="请输入地址"/>
        </el-form-item>

        <el-form-item label="状态" prop="status">
          <el-select v-model="form.status" placeholder="请选择状态">
            <el-option v-for="dict in dict.type.sys_normal_disable" :key="dict.value" :label="dict.label"
                       :value="dict.value"></el-option>
          </el-select>
        </el-form-item>
<!--        <el-form-item label="备注" prop="remark">-->
<!--          <el-input v-model="form.remark" type="textarea" placeholder="请输入内容" />-->
<!--        </el-form-item>-->
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="submitForm">确 定</el-button>
        <el-button @click="cancel">取 消</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
import { listFactory, getFactory, delFactory, addFactory, updateFactory } from "@/api/his/factory";

import tool from '@/utils/tool.js'
export default {
  name: "Factory",
  dicts: ['sys_normal_disable'],
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
      multiple_del: false,
      // 显示搜索条件
      showSearch: true,
      // 总条数
      total: 0,
      // 岗位表格数据
      factoryList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,

        pageSize: 10,
        factoryName:null,
        keyword:null,
        mobile:null,
        status: null,
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
        factoryName: [
          { required: true, message: "厂家名称不能为空", trigger: "blur" }
        ],
        factoryNo: [
          { required: true, message: "厂家编码不能为空", trigger: "blur" }
        ],
        linkMan: [
          { required: true, message: "联系人不可以为空", trigger: "blur" }
        ],
        keyword: [
          { required: true, message: "查询关键字不能为空", trigger: "blur" }
        ],

        address: [
          { required: true, message: "地址不能为空", trigger: "blur" }
        ],

      }
    };
  },
  created() {
    this.getList();
  },
  methods: {
    /** 查询岗位列表 */
    getList() {
      this.loading = true;
      listFactory(this.queryParams).then(response => {
        this.factoryList = response.rows;
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
        factoryName: undefined,
        factoryNo: undefined,
        linkMan: undefined,
        mobile: undefined,
        keyword:undefined,
        address:undefined,
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
      this.handleQuery();
    },
    // 多选框选中数据
    handleSelectionChange(selection) {
      this.ids = selection.map(item => item.factoryId)
      this.single = selection.length!=1
      this.multiple = !selection.length
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.sign=1;
      this.reset();
      this.open = true;
      this.title = "添加厂家";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.sign=2;
      this.reset();
      //this.form = row; //少查了一次数据库
      this.form=tool(row);//通过复制避免出现问题
      this.open = true;
      this.title = "修改厂家";


      // const factoryId = row.factoryId || this.ids
      // getFactory(factoryId).then(response => {
      //   this.form = response.data;
      //   this.open = true;
      //   this.title = "修改厂家";
      // });
    },
    /** 提交按钮 */
    submitForm: function() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.sign==2) {
            updateFactory(this.form).then(response => {
              this.$modal.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else if(this.sign==1) {
            addFactory(this.form).then(response => {
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
      const factoryIds = row.factoryId || this.ids;
      this.$modal.confirm('是否确认删除岗位编号为"' + factoryIds + '"的数据项？').then(function() {
        return delFactory(factoryIds);
      }).then(() => {
        this.getList();
        this.$modal.msgSuccess("删除成功");
      }).catch(() => {});
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('his/factory/export', {
        ...this.queryParams
      }, `post_${new Date().getTime()}.xlsx`)
    }
  }
};
</script>
