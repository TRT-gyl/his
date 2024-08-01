<template>
  <div>
    <el-row style="margin-top: 25px">
      <el-card shadow="hover">
        <el-form ref="queryForm" :model="queryParams" size="medium" label-width="100px">
          <el-row :gutter="12">
            <el-col :span="4" :offset="1">
              <el-form-item label="药品名称" prop="name">
                <el-input v-model="queryParams.name" placeholder="请输入药品名称" clearable :style="{ width: '100%' }">
                </el-input>
              </el-form-item>
            </el-col>
            <el-col :span="6" :offset="1">
              <el-form-item label="交易时间">
                <el-date-picker type="daterange" v-model="dateRange" format="yyyy-MM-dd" value-format="yyyy-MM-dd"
                  :style="{ width: '100%' }" start-placeholder="开始日期" end-placeholder="结束日期" range-separator="至"
                  clearable>
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
          <el-tabs type="card" @tab-click="initTable" :value="currentTalbe" ref="tabs" >
            <el-tab-pane label="药品销售列表" name="saleTable">
              <el-table :data="medicineSaleRecordsList" border style="width: 100%" :default-sort="{prop: 'createTime', order: 'descending'}"> 
                <el-table-column prop="medicineId" label="药品ID" width="70">
                </el-table-column>
                <el-table-column prop="name" label="药品名称">
                </el-table-column>
                <el-table-column prop="price" label="药品价格(单位：￥)">
                </el-table-column>
                <el-table-column prop="quantity" label="交易数量(单位：盒)">
                </el-table-column>
                <el-table-column prop="amount" label="交易总价(单位:￥)">
                </el-table-column>
                <el-table-column prop="createTime" label="交易时间" sortable>
                </el-table-column>
              </el-table>
            </el-tab-pane>
            <el-tab-pane label="统计列表" name="statisticTable">
              <el-table :data="medicineSaleStatisticList" border style="width: 100%">
                <el-table-column prop="medicineId" label="药品ID" width="70">
                </el-table-column>
                <el-table-column prop="name" label="药品名称">
                </el-table-column>
                <el-table-column prop="amount" label="交易总金额((单位:￥))">
                </el-table-column>
                <el-table-column prop="quantity" label="交易总数((单位：盒))">
                </el-table-column>
              </el-table>
              <el-card class="box-card" v-model="summary">
                合计：销售总量 <span style='color: chocolate'>{{summary.saleNumbers}}盒</span> 销售总金额 <span style='color:red'>{{summary.saleTotalPrice}}￥</span>
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
  import { listMedicineSaleRecords, listMedicineStatisticRecords } from "@/api/his/statistic";
export default {
  props: [],
  data() {
    return {
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        name: undefined,
      },
      dateRange: [],
      total: 0,
      salesTotal:0,
      statisticTotal:0,
      medicineSaleRecordsList: [],
      medicineSaleStatisticList: [],
      currentTalbe: 'saleTable',
      //表单参数
      form: {},
      // 表单校验
      loading: true,
      rules: {
        medicineName: [
          { required: true, message: "药品名称不能为空", trigger: "blur" }
        ],
        dateRange: [{
          required: true,
          message: '日期范围不能为空',
          trigger: 'change'
        }]
      },
      summary:{
        saleNumbers:0,
        saleTotalPrice: 0.
      }
    }
  },
  computed: {},
  watch: {},
  created() { 
    this.getListMedicineSaleRecords();
  },
  mounted() {
  },
  methods: {
    /**得到汇总信息 */
    getSummary(){
      for(let records of this.medicineSaleStatisticList){
        this.summary.saleNumbers = this.summary.saleNumbers+records.quantity;
        this.summary.saleTotalPrice = this.summary.saleTotalPrice+records.amount;
      }  
    },
    /** 查询记录列表 */
    getListMedicineSaleRecords() {
      this.loading = true;
      listMedicineSaleRecords(this.addDateRange(this.queryParams, this.dateRange)).then(response => {
        this.medicineSaleRecordsList = response.rows;
        console.log(this.medicineSaleRecordsList);
        this.salesTotal = response.total;
        this.total = this.salesTotal;
        this.loading = false;
      }
      );
    },
    getListMedicineStatisticRecords() {
      this.loading = true;
      listMedicineStatisticRecords(this.addDateRange(this.queryParams, this.dateRange)).then(response => {
       
        this.medicineSaleStatisticList = response.rows;
        console.log(this.medicineSaleStatisticList);
        this.statisticTotal = response.total;
        this.total = this.statisticTotal;
        this.loading = false;
        this.getSummary();

      }
      );
    },
    getPage(){
      let name = this.$refs.tabs.value;
      if(name=='saleTable'){
        this.getListMedicineSaleRecords();
      }
      else{
        this.getListMedicineStatisticRecords();
      }
    },
    /** 搜索按钮操作 */
    handleQuery() {
      this.queryParams.pageNum = 1;
      let name = this.$refs.tabs.value;
      this.medicineSaleRecordsList=[];
      this.medicineSaleStatisticList=[];
      this.summary.saleNumbers = 0;
      this.summary.saleTotalPrice = 0;
      console.log(name);
      if(name=='saleTable'){
        this.getListMedicineSaleRecords();
      }
      else{
        this.getListMedicineStatisticRecords();
      }
      
    },
    /** 重置按钮操作 */
    resetQuery() {
      this.dateRange = [];
      this.resetForm("queryForm");
      this.handleQuery();
    },
    initTable(tab){
      console.log(tab.name);
      this.currentTalbe = tab.name;
      if(tab.name=='saleTable'){
        if(this.medicineSaleRecordsList.length==0){
          this.getListMedicineSaleRecords();
        }
        else{
          this.total = this.salesTotal;
        }
      }
      else{
        if(this.medicineSaleStatisticList.length==0){
          this.getListMedicineStatisticRecords();
        }
        else{
          this.total = this.statisticTotal;
        }
      }
    }
      
    }
  }


</script>
<style>
</style>
