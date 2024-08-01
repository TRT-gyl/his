<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="患者姓名" prop="patientName">
        <el-input v-model="queryParams.patientName" placeholder="请输入患者姓名" clearable @keyup.enter.native="handleQuery" />
      </el-form-item>
      <el-form-item label="手机号" prop="tel">
        <el-input v-model="queryParams.tel" placeholder="请输入手机号" clearable @keyup.enter.native="handleQuery" />
      </el-form-item>
      <el-form-item label="身份证号" prop="idCard">
        <el-input v-model="queryParams.idCard" placeholder="请输入身份证号" clearable @keyup.enter.native="handleQuery" />
      </el-form-item>
      <el-form-item>
        <el-button type="primary" icon="el-icon-search" size="mini" @click="handleQuery">搜索</el-button>
        <el-button icon="el-icon-refresh" size="mini" @click="resetQuery">重置</el-button>
      </el-form-item>
    </el-form>

    <el-table v-loading="loading" :data="patientList">
      <el-table-column label="姓名" align="center" prop="patientName" />
      <el-table-column label="电话" align="center" prop="tel" />
      <el-table-column label="身份证号" align="center" prop="idCard" />
      <el-table-column label="出生年月" align="center" prop="patientDate" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.patientDate, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="信息状态" align="center" prop="address" />
      <el-table-column label="年龄" align="center" prop="age" />
      <el-table-column label="性别" align="center" prop="gender">
        <template slot-scope="scope">
          <dict-tag :options="dict.type.his_doctor_sex" :value="scope.row.doctSex" />
        </template>
      </el-table-column>
      <el-table-column label="创建时间" align="center" prop="createTime" />
    </el-table>

    <pagination v-show="total > 0" :total="total" :page.sync="queryParams.pageNum" :limit.sync="queryParams.pageSize"
      @pagination="getList" />
   
  </div>
</template>
  
  <script>
  import { listPatient, addPatient, updatePatient } from "@/api/his/patient";
  
  export default {
    dicts: ['his_doctor_sex'],
    name: "Patient",
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
        // 患者表格数据
        patientList: [],
        // 弹出层标题
        title: "",
        // 是否显示弹出层
        open: false,
        // 查询参数
        queryParams: {
          pageNum: 1,
          pageSize: 10,
          patientName: null,
          tel: null,
          idCard: null,
          age: null,
          gender: null,
          address: null,
          patientDate: null,
          createTime: null
        },
        // 表单参数
        form: {},
        // 表单校验
        rules: {
          patientName: [
            { required: true, message: "患者姓名不能为空", trigger: "blur" }
          ],
          tel: [
            { required: true, message: "患者电话不能为空", trigger: "blur" }
          ],
          idCard: [
            { required: true, message: "患者身份证不能为空", trigger: "blur" }
          ],
        }
      };
    },
    created() {
      this.getList();
    },
    methods: {
      /** 查询患者列表 */
      getList() {
        this.loading = true;
        listPatient(this.queryParams).then(response => {
          this.patientList = response.rows;
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
          patientId: null,
          patientName: null,
          tel: null,
          idCard: null,
          age: null,
          gender: "0",
          address: null,
          patientDate: null,
          delFlag: null,
          createBy: null,
          createTime: null,
          updateBy: null,
          updateTime: null,
          remark: null
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
  
      /** 提交按钮 */
      submitForm() {
        this.$refs["form"].validate(valid => {
          if (valid) {
            if (this.form.patientId != null) {
              updatePatient(this.form).then(response => {
                this.$modal.msgSuccess("修改成功");
                this.open = false;
                this.getList();
              });
            } else {
              addPatient(this.form).then(response => {
                this.$modal.msgSuccess("新增成功");
                this.open = false;
                this.getList();
              });
            }
          }
        });
      },
  
      /** 导出按钮操作 */
      handleExport() {
        this.download('his/patient/export', {
          ...this.queryParams
        }, `patient_${new Date().getTime()}.xlsx`)
      }
    }
  };
  </script>
  