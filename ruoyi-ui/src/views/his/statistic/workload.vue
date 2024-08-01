<template>
    <div>
        <el-row style="margin-top: 25px">
            <el-card shadow="hover">
                <el-form ref="queryForm" :model="queryParams" size="medium" label-width="100px">
                    <el-row :gutter="12">
                        <el-col :span="4" :offset="1">
                            <el-form-item label="医生姓名" prop="doctName">
                                <el-input v-model="queryParams.doctName" placeholder="请输入医生姓名" clearable
                                    :style="{ width: '100%' }">
                                </el-input>
                            </el-form-item>
                        </el-col>
                        <el-col :span="6" :offset="1">
                            <el-form-item label="接诊时间">
                                <el-date-picker type="daterange" v-model="dateRange" format="yyyy-MM-dd"
                                    value-format="yyyy-MM-dd" :style="{ width: '100%' }" start-placeholder="开始日期"
                                    end-placeholder="结束日期" range-separator="至" clearable>
                                </el-date-picker>
                            </el-form-item>
                        </el-col>
                        <el-col :span="4">
                            <el-form-item size="large">
                                <el-button type="primary" @click="handleQuery">搜索</el-button>
                                <el-button @click="resetQuery">重置</el-button>
                            </el-form-item>
                        </el-col>
                    </el-row>
                </el-form>
                <el-row>
                    <el-tabs type="card" @tab-click="initTable" :value="currentTalbe" ref="tabs">
                        <el-tab-pane label="医生接诊记录列表" name="registrationTable">
                            <el-table :data="registrationRecordsList" border style="width: 100%"
                                :default-sort="{prop: 'scheduleDate', order: 'descending'}">
                                <el-table-column prop="registrationId" label="挂号ID" width="70">
                                </el-table-column>
                                <el-table-column prop="doctId" label="医生编号">
                                </el-table-column>
                                <el-table-column prop="doctName" label="医生姓名">
                                </el-table-column>
                                <el-table-column prop="regPrice" label="挂号费用（单位：￥）">
                                </el-table-column>
                                <el-table-column prop="patientName" label="患者姓名">
                                </el-table-column>
                                <el-table-column prop="scheduleDate" label=" 接诊时间" sortable>
                                </el-table-column>
                            </el-table>
                        </el-tab-pane>
                        <el-tab-pane label="项目统计列表" name="statisticTable">
                            <el-table :data="registrationStatisticList" border style="width: 100%">
                                <el-table-column prop="registrationId" label="挂号ID" width="70">
                                </el-table-column>
                                <el-table-column prop="doctId" label="医生编号">
                                </el-table-column>
                                <el-table-column prop="doctName" label="医生姓名">
                                </el-table-column>
                                <el-table-column prop="regPrice" label="挂号总额（单位：￥）">
                                </el-table-column>
                                <el-table-column prop="totalTimes" label="接诊数量">
                                </el-table-column>
                            </el-table>
                            <el-card class="box-card" v-model="summary">
                                合计：接诊总次数 <span style='color: chocolate'>{{summary.registrationNumbers}}次</span> 总挂号费 <span
                                    style='color:red'>{{summary.totalPrice}}￥</span>
                            </el-card>
                        </el-tab-pane>
                        <pagination v-show="total>0" :total="total" :page.sync="queryParams.pageNum"
                            :limit.sync="queryParams.pageSize" @pagination="getPage" />
                    </el-tabs>
                </el-row>
            </el-card>
        </el-row>
    </div>
</template>
<script>
import { listRegistrationRecords, listRegistrationStatisticRecords } from "@/api/his/statistic";
export default {
    props: [],
    data() {
        return {
            queryParams: {
                pageNum: 1,
                pageSize: 10,
                doctName: undefined,
            },
            dateRange: [],
            total: 0,
            registrationTotal: 0,
            statisticTotal: 0,
            registrationRecordsList: [],
            registrationStatisticList: [],
            currentTalbe: 'registrationTable',
            //表单参数
            form: {},
            // 表单校验
            loading: true,
            /*rules: {
                medicineName: [
                    { required: true, message: "药品名称不能为空", trigger: "blur" }
                ],
                dateRange: [{
                    required: true,
                    message: '日期范围不能为空',
                    trigger: 'change'
                }]
            },*/
            summary: {
                registrationNumbers: 0,
                totalPrice: 0.
            }
        }
    },
    computed: {},
    watch: {},
    created() {
        this.getListRegistrationRecords();
    },
    mounted() {
    },
    methods: {
        /**得到汇总信息 */
        getSummary() {
            this.summary.totalPrice = 0;
            this.summary.registrationNumbers = 0;
            for (let records of this.registrationStatisticList) {
                this.summary.registrationNumbers = this.summary.registrationNumbers + records.totalTimes;
                this.summary.totalPrice = this.summary.totalPrice + records.regPrice;
            }
        },
        /** 查询记录列表 */
        getListRegistrationRecords() {
            this.loading = true;
            listRegistrationRecords(this.addDateRange(this.queryParams, this.dateRange)).then(response => {
                this.registrationRecordsList = response.rows;
                this.registrationTotal = response.total;
                this.total = this.registrationTotal;
                this.loading = false;
            }
            );
        },
        getListRegistrationStatisticRecords() {
            this.loading = true;
            listRegistrationStatisticRecords(this.addDateRange(this.queryParams, this.dateRange)).then(response => {
                this.registrationStatisticList = response.rows;
                console.log(this.registrationStatisticList);
                this.statisticTotal = response.total;
                this.total = this.statisticTotal;
                this.loading = false;
                this.getSummary();

            }
            );
        },
        getPage() {
            let name = this.$refs.tabs.value;
            if (name == 'registrationTable') {
                this.getListRegistrationRecords();
            }
            else {
                this.getListRegistrationStatisticRecords();
            }
        },
        /** 搜索按钮操作 */
        handleQuery() {
            this.queryParams.pageNum = 1;
            let name = this.$refs.tabs.value;
            this.registrationRecordsLsit = [];
            this.registrationStatisticList = [];
            console.log(name);
            if (name == 'registrationTable') {
                this.getListRegistrationRecords();
            }
            else {
                this.getListRegistrationStatisticRecords();
            }

        },
        /** 重置按钮操作 */
        resetQuery() {
            this.dateRange = [];
            this.resetForm("queryForm");
            this.handleQuery();
        },
        initTable(tab) {
            console.log(tab.name);
            this.currentTalbe = tab.name;
            if (tab.name == 'registrationTable') {
                if (this.registrationRecordsList.length == 0) {
                    this.getListRegistrationRecords();
                }
                else {
                    this.total = this.registrationTotal;
                }
            }
            else {
                if (this.registrationStatisticList.length == 0) {
                    this.getListRegistrationStatisticRecords();
                }
                else {
                    this.total = this.statisticTotal;
                }
            }
        }

    }
}


</script>
<style>
</style>
  