<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="rQueryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="单据号" prop="billsid" >
        <el-input
          v-model="queryParams.billsid"
          clearable
          @keyup.enter.native="handleQuery"
          placeholder="请输入单据号"
        />
      </el-form-item>
      <el-form-item label="药品名称" prop="medicineName">
        <el-input
          v-model="queryParams.medicineName"
          clearable
          @keyup.enter.native="handleQuery"
          placeholder="请输入药品名"
        />
      </el-form-item>
      <el-form-item label="药品类型" prop="medicineType">
        <el-select v-model="queryParams.medicineType" placeholder="请选择药品类型" clearable >
          <el-option
            v-for="sup in medicineTypes"
            :key="sup"
            :value="sup"
          />
        </el-select>
      </el-form-item>
      <el-form-item label="处方类型" prop="prescribe">
        <el-select v-model="queryParams.prescribe" placeholder="请选择处方类型" clearable >
          <el-option
            v-for="sup in prescribes"
            :key="sup"
            :value="sup"
          />
        </el-select>
      </el-form-item>
      <el-form-item label="日期范围" prop="createTime">
        <el-date-picker type="daterange" v-model="createTime" format="yyyy-MM-dd"
                        value-format="yyyy-MM-dd" :style="{width: '100%'}" start-placeholder="开始日期" end-placeholder="结束日期"
                        range-separator="至" clearable @change="tiemChange" > </el-date-picker>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" icon="el-icon-search" size="mini" @click="getList">搜索</el-button>
        <el-button icon="el-icon-refresh" size="mini" @click="resetQuery">重置</el-button>
      </el-form-item>
    </el-form>

    <el-table v-loading="loading" border :data="HisPurchaseList" >
      <el-table-column label="药品ID" align="center" prop="hisMedicine.medicineId" />
      <el-table-column label="药品名称" align="center" prop="hisMedicine.medicineName" />
      <el-table-column label="采购量" align="center" prop="amount" />
      <el-table-column label="批发价" align="center" prop="wholesale" />
      <el-table-column label="批发额" align="center" prop="totalMoney" />
      <el-table-column label="批次号" align="center" prop="batch" />
      <el-table-column label="生产厂家" align="center" prop="hisMedicine.factory.factoryName" />
      <el-table-column label="药品类型" align="center" prop="hisMedicine.medicineType" />
      <el-table-column label="处方类型" align="center" prop="hisMedicine.prescribe" />
      <el-table-column label="创建时间" align="center" prop="createTime" >
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.createTime, '{y}-{m}-{d}')  }}</span>
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
  </div>
</template>

<script>
import { listPurchases } from "@/api/his/purchase";
import {listHisMedicine } from "@/api/his/warehousing";

export default {
  name: "BetchRepertory",
  data() {
    return {
      activeName: 'first',
      // 遮罩层
      loading: true,
      // 显示搜索条件
      showSearch: true,
      // 总条数
      total: 0,
      //药品类型集合
      medicineTypes: ["中草药","西药"
      ],
      //处方类型集合
      prescribes: ["中药处方","西药处方"
      ],
      // 订单及药品数据
      HisPurchaseList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      //药品集合
      medicines: [],
      // 查询参数
      createTime: null,
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        billsid: null,
        createTimeStart: null,
        createTimeEnd: null,
        medicineType: null,
        prescribe: null,
        medicineName: null,
        factoryName: null
      }
    };
  },
  created() {
    this.loading=false;
    this.getList();
  },
  methods: {
    /** 重置按钮操作 */
    resetQuery() {
      this.resetForm("rQueryForm");
      this.queryParams.pageNum = 1;
      this.getList();
    },
    getList(){
      listPurchases(this.queryParams).then(r=>{
        console.log(r);
        this.HisPurchaseList=r.rows;
        this.total=r.total;
      })
    },
    tiemChange(){
        this.queryParams.createTimeStart=this.createTime[0];
        this.queryParams.createTimeEnd=this.createTime[1];
    }
  }
};
</script>
