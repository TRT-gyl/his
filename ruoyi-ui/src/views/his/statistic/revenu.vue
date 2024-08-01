<template>
  <div>
    <el-card shadow="hover">
      <el-form ref="queryForm" :model="queryParams" size="medium" label-width="100px">
        <el-row :gutter="15" style="margin-top: 25px">
          <el-col :span="6" :offset="1">
            <el-form-item label="日期范围">
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
    </el-card>

    <el-row style="margin-top: 25px">

      <el-col :offset="1" :span="8">
        <el-card class="box-card1" v-model="summary">
          <p>净收入：<span style="color:brown">{{  summary.netGains  }}￥</span></p>
          <p>收支概况： 总收入<span style="color:brown">{{  summary.totalGains  }}￥</span> &nbsp;&nbsp;&nbsp;&nbsp; 
            总支出 <span style="color:darkgreen">{{  summary.totalSpend  }}￥ </span></p>
          <p>收入结构： 挂号收入 <span style="color:brown">{{  summary.gainsComposition.registrationGains  }}￥</span> 
          药品收入<span style="color:brown">{{
             summary.gainsComposition.medicineSaleGains  }}￥</span>
            检查收入<span style="color:brown">{{  summary.gainsComposition.examineGains  }}￥</span></p>
          <p>支出结构： 药品支出<span style="color:darkgreen">{{  summary.totalSpend  }}￥</span></p>
        </el-card>
      </el-col>

      <el-col :span="8">
        <el-card class="chart-box">
          <div slot="header" class="clearfix">
            <span>时段选择</span>
            <el-select placeholder="选择时段" @change="drawline" v-model="freq">
              <el-option label="近三天" value="days"></el-option>
              <el-option label="近一周" value="week"></el-option>
              <el-option label="近一个月" value="month"></el-option>
            </el-select>
          </div>
          <pie-chart ref="line" chartId="lineChart" :option="lineOption"></pie-chart>
        </el-card>
      </el-col>
    </el-row>

    <el-row :gutter="12" style="margin-top: 25px">
      <el-col :span="8">
        <el-card class="box-card">
          <div>
            <pie-chart ref="medicineCostComposition" chartId="medicineCostComposition" :option="medicineCostCompositionOption"></pie-chart>
          </div>
        </el-card>
      </el-col>
      <el-col :span="8">
        <el-card class="box-card">
          <div>
            <pie-chart ref="revenuComposition" chartId="revenuCompositionChart" :option="revenuCompositionOption"></pie-chart>
          </div>
        </el-card>
      </el-col>
      <el-col :span="8">
        <el-card class="box-card">
          <div>
            <pie-chart ref="deptGainsComposiiton" chartId="deptGainsComposiiton" :option="deptGainsComposiitonOption"></pie-chart>
          </div>
        </el-card>
      </el-col>

    </el-row>

  </div>

</template>
<script>
import PieChart from './charts/piechart.vue';
import {calculateRevenuSummary, getNetGainsTrend} from "@/api/his/statistic";
export default {
  components: { PieChart },
  props: [],
  data() {
    return {
      queryParams: {},
      depts: [],
      summary: {
        netGains: "",
        totalGains: "",
        totalSpend: "",
        gainsComposition: {
          registrationGains: "",
          medicineSaleGains: "",
          examineGains: "",
          medicineCost: ""
        },
        deptRevenus: [],
        medicinePurchaseRecords: []
      },
      dateRange: [],
      freq: 'days',
      medicineCostCompositionOption: {
        title: { text: "药品支出结构"},
        series: [
          {
            type: 'pie',
            data: []
          }
        ]
      },
      revenuCompositionOption: {
        title: { text: "收入结构"},
        series: [
          {
            type: 'pie',
            data: []
          }
        ]
      },
      deptGainsComposiitonOption: {
        title: { text: "各科室收入占比"},
        series: [
          {
            type: 'pie',
            data: []
          }
        ]
      },

      lineOption: {
        title: { text: "净收入趋势"},
        //横坐标为时间序列
        xAxis: {
          data: []
        },
        yAxis: {},
        series: [
          {
            data: [],
            type: 'line',
            smooth: true
          },
          {
            data: [],
            type: 'bar',
            smooth: true
          }
        ]
      }

    }
  },
  computed: {},
  watch: {},
  //初始化图表
  created() {
    
    
  },
  mounted() {
    this.getSummary();
    this.getTrend();
  },

  methods: {
    drawline() {
      this.getTrend();
    },
    /**得到汇总信息 */
    getSummary() {
      calculateRevenuSummary({startDate: this.dateRange[0], endDate: this.dateRange[1]}).then(
        response => {
          console.log(response);
          /*this.summary = response.data;
          this.loading = false;*/
          console.log(response.data);
          this.summary = response.data;
          //console.log(summary);
          this.medicineCostCompositionOption.series[0].data=[];
          this.revenuCompositionOption.series[0].data=[];
          if(this.summary.gainsComposition.registrationGains>0){
            this.revenuCompositionOption.series[0].data.push({value: this.summary.gainsComposition.registrationGains, name: '挂号收入'});    
          }
          if(this.summary.gainsComposition.medicineSaleGains>0){
            this.revenuCompositionOption.series[0].data.push({value: this.summary.gainsComposition.medicineSaleGains, name: '药品收入'});
          }
          if(this.summary.gainsComposition.examineGains>0){
            this.revenuCompositionOption.series[0].data.push({value: this.summary.gainsComposition.examineGains, name: '检查项目收入'});

          }
          
          this.deptGainsComposiitonOption.series[0].data=[];
          for(let medicineCostRecord of this.summary.medicinePurchaseRecords){
             this.medicineCostCompositionOption.series[0].data.push({value: medicineCostRecord.totalMoney, name: medicineCostRecord.medicineName});
          }
          for(let deptRecords of this.summary.deptRevenus){
             this.deptGainsComposiitonOption.series[0].data.push({value: deptRecords.examineRevenu+deptRecords.regRevenu+deptRecords.medicineRevenu, name: deptRecords.deptName});
          }
          //this.medicineCostCompositionOption.series[0].data = [{value: 100, name: '支出'}]
          //console.log(this.medicineCostCompositionOption.series[0]);
          this.$refs.medicineCostComposition.myEcharts();
          this.$refs.revenuComposition.myEcharts();
          this.$refs.deptGainsComposiiton.myEcharts();

          

        }
      );
    },
    getTrend(){
      getNetGainsTrend({freq: this.freq}).then(
        response=>{
          console.log(response);
          let data = response.data;
          this.lineOption.xAxis.data=data.dateSeries;
          this.lineOption.series[0].data = [];
          this.lineOption.series[1].data = [];
          for(let revenuRecord of data.revenuSeries){
            this.lineOption.series[0].data.push(revenuRecord.netGains);
            this.lineOption.series[1].data.push(revenuRecord.netGains);
          }
          
          this.$refs.line.myEcharts();
        }

      )
    },

    /** 搜索按钮操作 */
    handleQuery() {
     this.getSummary();

    },
    /** 重置按钮操作 */
    resetQuery() {
      this.dateRange=[];
      this.resetForm("queryForm");
      this.handleQuery();
    },


  }
}


</script>
<style>
.text {
  font-size: 14px;
}

.item {
  margin-bottom: 18px;
}

.clearfix:before,
.clearfix:after {
  display: table;
  content: "";
}

.clearfix:after {
  clear: both
}

.box-card1 {
  width: 480px;
}

.box-card2 {
  width: 480px;
  padding: auto;
}

.chart-box {
  width: 1000px;
  height: 500px;
}
</style>
