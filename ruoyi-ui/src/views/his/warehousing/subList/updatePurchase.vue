<template>
  <div class="app-container">
    <el-form :model="initParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="单据号" prop="billsid">
        <el-input
          v-model="initParams.billsid"
          :disabled="true"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="制单人" prop="createBy">
        <el-input
          v-model="initParams.createBy"
          :disabled="true"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="目前操作人" prop="createBy" label-width="100px">
        <el-input
          v-model="this.$store.state.user.name"
          :disabled="true"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="制单时间" prop="createTime">
        <el-input
          v-model="initParams.createTime"
          @keyup.enter.native="handleQuery" :disabled="true"
        />
      </el-form-item>
      <el-form-item label="总批发额" prop="totalMoney">
        <el-input
          v-model="initParams.totalMoney"
          :disabled="true"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="供应商" prop="category">
        <el-select v-model="initParams.suppliersId" placeholder="请选择供应商" clearable >
          <el-option
            v-for="sup in suppliers"
            :key="sup.supplierId"
            :label="sup.supplierName"
            :value="sup.supplierId"
          />
        </el-select>
      </el-form-item>
    </el-form>

    <el-row :gutter="10" class="mb8">
      <el-col :span="1.5">
        <el-button
          type="primary"
          plain
          icon="el-icon-plus"
          size="mini"
          @click="handleUpdate"
          v-hasPermi="['his:purchase:add']"
        >修改采购申请</el-button>
      </el-col>
    </el-row>

    <el-table v-loading="loading" :data="medcs" >
      <el-table-column label="药品ID" align="center" prop="hisMedicine.medicineId" />
      <el-table-column label="药品名称" align="center" prop="hisMedicine.medicineName" />
      <el-table-column label="生产厂家" align="center" prop="hisMedicine.factory.factoryName" />
      <el-table-column label="数量" align="center" prop="" width="150">
        <template slot-scope="scope">
        <el-input-number v-model="medcs[scope.$index].amount"   size="small" @change="totalMoney(scope)"/></template>
      </el-table-column>
      <el-table-column label="单位" align="center" prop="hisMedicine.unit" />
      <el-table-column label="批发价" align="center" prop="" width="150" >
        <template slot-scope="scope">
            <el-input-number v-model="medcs[scope.$index].wholesale"    size="small" :precision="2" :min="0" @change="totalMoney(scope)"/>
        </template>
      </el-table-column >
      <el-table-column label="批发额" align="center" prop="" width="100px" :precision="2">
        <template slot-scope="scope">
          <el-input
            v-model="medcs[scope.$index].totalMoney" disabled
          />
        </template>
      </el-table-column>
      <el-table-column label="批次号" align="center" prop=""  width="80px">
        <template slot-scope="scope">
          <el-input
            v-model="medcs[scope.$index].batch"
          />
        </template>
      </el-table-column>
      <el-table-column label="备注" align="center" prop="" width="150px" >
        <template slot-scope="scope">
          <el-input
            v-model="medcs[scope.$index].remark"
            clearable
          />
        </template>
      </el-table-column>
    </el-table>
  </div>
</template>

<script>
import { listPurchases, getPurchase, delPurchase, addPurchase, updatePurchase } from "@/api/his/purchase";
import {listHisSupplie,listHisMedicine,updateWarehousing } from "@/api/his/warehousing";
import { getWarehousing, updateWarehousings } from '../../../../api/his/warehousing'

export default {
  name: "Purchase",
  data() {
    return {
      // 遮罩层
      loading: true,
      // 非单个禁用
      single: true,
      // 非多个禁用
      multiple: true,
      // 显示搜索条件
      showSearch: true,
      // 总条数
      total: 0,
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      //状态：当状态大于等于1时不能修改
      status: null,
      //供应商集合
      suppliers: [],
      //订单数据集合
      medcs: [],
      //采购入库初始的参数
      initParams:{
        id: null,
        billsid: null,
        suppliersId: null,
        createBy: null,
        createTime: null,
        totalMoney: 0,
      },
      //查询订单列表的参数
      purchaseParams:{
        pageNum: 1,
        pageSize: 10,
        pwId: 0
      },
      //供应商参数
      supper: {
        supplierId: null,
        supplierName: null,
        linkMan: null,
        mobile: null,
        phone: null,
        account: null,
        address: null,
        status: "0",
        createBy: null,
        createTime: null,
        updateBy: null,
        updateTime: null
      },
      //订单参数
      purchase: {
        medicineId: 0,
        amount: 0,
        wholesale: 0,
        batch: null,
        totalMoney: 0,
        remark: null
      },
      // 表单参数
      form: {}
    };
  },
  created() {
    const id = this.$route.params && this.$route.params.id;
    //查询供应商列表
    listHisSupplie().then(response=>{
      this.suppliers=response.data;
    });
    //查询采购入库的信息
    getWarehousing(id).then(response=>{
      this.initParams.id=response.data.id;
      this.initParams.billsid=response.data.billsid;
      this.initParams.createBy=response.data.createBy;
      this.initParams.suppliersId=response.data.suppliersId;
      this.initParams.createTime=response.data.createTime;
      this.initParams.totalMoney=response.data.totalMoney;
      this.purchaseParams.pwId=response.data.id;
      this.status=response.data.status
      //查询采购入库的订单列表
      this.getList();
    });
    this.loading=false;
  },
  methods: {
    //计算单个药品种类的批发总价并计算全部总价
    totalMoney(scope){
      console.log(scope);
      this.initParams.totalMoney=0;
      for (let pur of this.medcs) {
        pur.totalMoney=pur.amount*pur.wholesale;
        this.initParams.totalMoney+=pur.totalMoney;
      }
    },
    /** 查询药品采购列表 */
    getList() {
      this.loading = true;
      listPurchases(this.purchaseParams).then(response => {
        this.medcs=response.rows;
        this.loading = false;
      });
    },
    //最后修改采购申请
    handleUpdate(){
      console.log(this.status);
      if(this.status>=1){
        this.$alert("当前申请已经提交或已经入库，不能修改。","警告",{
          confirmButtonText: '确定',
          type: 'warning'
        });
        return;
      }
      //修改采购入库
      let params={
        hisPurchasWarehousing:{
          id: this.initParams.id,
          totalMoney:this.initParams.totalMoney,
          suppliersId: this.initParams.suppliersId,
          createBy: this.$store.state.user.name
        },
        hisPurchaseList: this.medcs
      };
      this.loading=true;
      updateWarehousings(params).then(response=>{
        this.loading=false;
        this.$message.success("修改成功");
      })
    }
  }
};
</script>
