<template>
    <div>
        <el-row style="margin-top: 25px">
            <el-card shadow="hover">
                <el-form ref="queryForm" :model="queryParams" size="medium" label-width="100px">
                    <el-row :gutter="12">
                        <el-col :span="4" :offset="1">
                            <el-form-item label="项目名称" prop="itemName">
                                <el-input v-model="queryParams.itemName" placeholder="请输入检查项目名称" clearable
                                    :style="{ width: '100%' }">
                                </el-input>
                            </el-form-item>
                        </el-col>

                        <el-col :span="4" :offset="1">
                            <el-form-item label="患者名称" prop="patientName">
                                <el-input v-model="queryParams.patientName" placeholder="请输入患者名称" clearable
                                    :style="{ width: '100%' }">
                                </el-input>
                            </el-form-item>
                        </el-col>

                        <el-col :span="6" :offset="1">
                            <el-form-item label="检查时间">
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
                        <el-tab-pane label="检查项目列表" name="examineTable">
                            <el-table :data="examineRecordsList" border style="width: 100%"
                                :default-sort="{prop: 'createTime', order: 'descending'}">
                                <el-table-column prop="itemId" label="检查项目ID" width="70">
                                </el-table-column>
                                <el-table-column prop="itemName" label="项目名称">
                                </el-table-column>
                                <el-table-column prop="totalPrice" label="项目价格(单位：￥)">
                                </el-table-column>
                                <el-table-column prop="patientName" label="患者姓名">
                                </el-table-column>
                                <el-table-column prop="status" label="支付状态" >
                                </el-table-column>
                                <el-table-column prop="createTime" label="创建时间" sortable>
                                </el-table-column>
                            </el-table>
                        </el-tab-pane>
                        <el-tab-pane label="项目统计列表" name="statisticTable">
                            <el-table :data="examineStatisticList" border style="width: 100%">
                                <el-table-column prop="itemId" label="检查项目ID" width="70">
                                </el-table-column>
                                <el-table-column prop="itemName" label="项目名称">
                                </el-table-column>
                                <el-table-column prop="totalPrice" label="总金额(单位：￥)">
                                </el-table-column>
                                <el-table-column prop="totalTimes" label="总次数">
                                </el-table-column>
                            </el-table>
                            <el-card class="box-card" v-model="summary">
                                合计：检查总次数 <span style='color: chocolate'>{{summary.examineNumbers}}次</span> 总金额 <span
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
import { listExamineRecords, listExamineStatisticRecords } from "@/api/his/statistic";
export default {
    props: [],
    data() {
        return {
            queryParams: {
                pageNum: 1,
                pageSize: 10,
                itemName: undefined,
                patientName: undefined,

            },
            dateRange: [],
            total: 0,
            examineTotal: 0,
            statisticTotal: 0,
            examineRecordsList: [],
            examineStatisticList: [],
            currentTalbe: 'examineTable',
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
                examineNumbers: 0,
                totalPrice: 0.
            }
        }
    },
    computed: {},
    watch: {},
    created() {
        this.getListExamineRecords();
    },
    mounted() {
    },
    methods: {
        /**得到汇总信息 */
        getSummary() {
            this.summary.totalPrice = 0;
            this.summary.examineNumbers = 0;
            for (let records of this.examineStatisticList) {
                this.summary.examineNumbers = this.summary.examineNumbers + records.totalTimes;
                this.summary.totalPrice = this.summary.totalPrice + records.totalPrice;
            }
        },
        /** 查询记录列表 */
        getListExamineRecords() {
            this.loading = true;
            listExamineRecords(this.addDateRange(this.queryParams, this.dateRange)).then(response => {
                this.examineRecordsList = response.rows;
                console.log(this.examineRecordsList);
                for(let records of this.examineRecordsList){
                    if(records.status=='0'){
                        records.status='未支付';
                    }
                    else{
                        records.status='已支付';
                    }
                }
                this.examineTotal = response.total;
                this.total = this.examineTotal;
                this.loading = false;
            }
            );
        },
        getListExamineStatisticRecords() {
            this.loading = true;
            listExamineStatisticRecords(this.addDateRange(this.queryParams, this.dateRange)).then(response => {
                this.examineStatisticList = response.rows;
                console.log(this.examineStatisticList);
                this.statisticTotal = response.total;
                this.total = this.statisticTotal;
                this.loading = false;
                this.getSummary();

            }
            );
        },
        getPage() {
            let name = this.$refs.tabs.value;
            if (name == 'examineTable') {
                this.getListExamineRecords();
            }
            else {
                this.getListExamineStatisticRecords();
            }
        },
        /** 搜索按钮操作 */
        handleQuery() {
            this.queryParams.pageNum = 1;
            let name = this.$refs.tabs.value;
            this.examineRecordsList = [];
            this.examineStatisticList = [];
            console.log(name);
            if (name == 'examineTable') {
                this.getListExamineRecords();
            }
            else {
                this.getListExamineStatisticRecords();
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
            if (tab.name == 'examineTable') {
                if (this.examineRecordsList.length == 0) {
                    this.getListExamineRecords();
                }
                else {
                    this.total = this.examineTotal;
                }
            }
            else {
                if (this.examineStatisticList.length == 0) {
                    this.getListExamineStatisticRecords();
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
  