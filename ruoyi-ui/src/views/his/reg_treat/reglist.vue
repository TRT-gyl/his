<template>
    <div class="app-container">
        <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch"
            label-width="68px">
            <el-form-item label="挂号日期" prop="scheduleDate">
                <el-date-picker clearable v-model="queryParams.scheduleDate" type="date" value-format="yyyy-MM-dd"
                    placeholder="请选择挂号日期">
                </el-date-picker>
            </el-form-item>
            <el-form-item label="挂号时段" prop="scheduleTime">
                <el-select v-model="queryParams.scheduleTime" placeholder="请选择挂号时段" clearable>
                    <el-option v-for="dict in dict.type.his_schedule_time" :key="dict.value" :label="dict.label"
                        :value="dict.value" />
                </el-select>
            </el-form-item>
            <el-form-item label="挂号状态" prop="registrationStatus">
                <el-select v-model="queryParams.registrationStatus" placeholder="请选择挂号状态" clearable>
                    <el-option v-for="dict in dict.type.his_reg_status" :key="dict.value" :label="dict.label"
                        :value="dict.value" />
                </el-select>
            </el-form-item>
            <el-form-item label="患者姓名" prop="patientName">
                <el-input v-model="queryParams.patientName" placeholder="请输入患者姓名" clearable
                    @keyup.enter.native="handleQuery" />
            </el-form-item>
            <el-form-item>
                <el-button type="primary" icon="el-icon-search" size="mini" @click="handleQuery">搜索</el-button>
                <el-button icon="el-icon-refresh" size="mini" @click="resetQuery">重置</el-button>
            </el-form-item>
        </el-form>

        <el-table v-loading="loading" :data="registrationList">
            <el-table-column label="患者姓名" align="center" prop="patientName" />
            <el-table-column label="挂号科室" align="center" prop="deptName" />
            <el-table-column label="医生姓名" align="center" prop="doctName" />
            <el-table-column label="挂号费" align="center" prop="regPrice" />
            <el-table-column label="流水编号" align="center" prop="registrationId" />
            <el-table-column label="挂号状态" align="center" prop="registrationStatus">
                <template slot-scope="scope">
                    <dict-tag :options="dict.type.his_reg_status" :value="scope.row.registrationStatus" />
                </template>
            </el-table-column>
            <el-table-column label="就诊日期" align="center" prop="scheduleDate" width="180">
                <template slot-scope="scope">
                    <span>{{ parseTime(scope.row.scheduleDate, '{y}-{m}-{d}') }}</span>
                </template>
            </el-table-column>
            <el-table-column label="挂号类型" align="center" prop="scheduleType" />
            <el-table-column label="挂号时段" align="center" prop="scheduleTime">
                <template slot-scope="scope">
                    <dict-tag :options="dict.type.his_schedule_time" :value="scope.row.scheduleTime" />
                </template>
            </el-table-column>
            <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
                <template slot-scope="scope">
                    <el-button size="mini" type="success" icon="el-icon-check" v-if="scope.row.registrationStatus == '0'"  @click="handleUpdate(scope.row)">收费
                    </el-button>
                    <el-button size="mini" type="danger" icon="el-icon-close" v-if="scope.row.registrationStatus == '1'" @click="handleDelete(scope.row)">退费
                    </el-button>
                </template>
            </el-table-column>
        </el-table>

        <pagination v-show="total>0" :total="total" :page.sync="queryParams.pageNum" :limit.sync="queryParams.pageSize"
            @pagination="getList" />

    </div>
</template>
  
  <script>
  import { listRegistration, getRegistration, addRegistration, updateRegistration } from "@/api/his/registration";
  
  export default {
    name: "Registration",
    dicts: ['his_schedule_time', 'his_reg_status'],
    data() {
      return {
        // 显示搜索条件
        showSearch: true,
        // 总条数
        total: 0,
        // 挂号列表表格数据
        registrationList: [],
        // 查询参数
        queryParams: {
          pageNum: 1,
          pageSize: 10,
          scheduleDate: null,
          scheduleTime: null,
          scheduleType: null,
          deptName: null,
          registrationStatus: null,
          patientName: null
        },
      };
    },
    created() {
      this.getList();
    },
    methods: {
      /** 查询挂号列表列表 */
      getList() {
        this.loading = true;
        listRegistration(this.queryParams).then(response => {
          this.registrationList = response.rows;
          this.total = response.total;
          this.loading = false;
        });
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
      /** 退费按钮操作 */
      handleDelete(row) {
        console.log("退费",row);
        updateRegistration(row).then(r => {
            if(r.code==200){
                this.$modal.msgSuccess("退号已成功");
                this.resetQuery();
            }
      });
      },
      /** 收费按钮操作 */
      handleUpdate(row) {
        console.log("收费",row);
        updateRegistration(row).then(r => {
            if(r.code==200){
                this.$modal.msgSuccess("收费成功");
                this.resetQuery();
            }
      });
      },
 
    }
  };
  </script>
  