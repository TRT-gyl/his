<template>

  <div class="app-container">
    <el-form :model="queryParams" ref="rQueryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">
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
      <el-form-item>
        <el-button type="primary" icon="el-icon-search" size="mini" @click="getMedicineList">搜索</el-button>
        <el-button icon="el-icon-refresh" size="mini" @click="resetQuery">重置</el-button>
      </el-form-item>
    </el-form>

    <el-table v-loading="loading" border :data="medicines" >
      <el-table-column label="药品ID" align="center" prop="medicineId" />
      <el-table-column label="药品名称" align="center" prop="medicineName" />
      <el-table-column label="库存量" align="center" prop="stock" />
      <el-table-column label="预警值" align="center" prop="warn" />
      <el-table-column label="生产厂家" align="center" prop="factory.factoryName" />
      <el-table-column label="药品编码" align="center" prop="medicineNo" />
      <el-table-column label="药品类型" align="center" prop="medicineType" />
      <el-table-column label="处方类型" align="center" prop="prescribe" />
      <el-table-column label="单位" align="center" prop="unit" />
      <el-table-column label="换算量" align="center" prop="conversion" />
    </el-table>
    <pagination
      v-show="total>0"
      :total="total"
      :page.sync="queryParams.pageNum"
      :limit.sync="queryParams.pageSize"
      @pagination="getMedicineList"
    />
  </div>
</template>

<script>
import { listPurchase } from "@/api/his/purchase";
import {listHisMedicine } from "@/api/his/warehousing";

export default {
  name: "TotalRepertory",
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
      // 药品采购表格数据
      purchaseList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      //药品集合
      medicines: [],
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        medicineType: null,
        prescribe: null,
        medicineName: null
      },
    };
  },
  created() {
    this.medicinesSelect();
  },
  methods: {
    //标签切换事件
    changeClick(tab,event){
      /* console.log(tab);
      console.log(tab.name);
      console.log(event); */
      //this.$router.push("/his/warehousing/list");
    },
    // 取消按钮
    cancel() {
      this.open = false;
      this.queryParams.Keyword=null;
      this.queryParams.pageNum=1;
      this.queryParams.pageSize=10;
    },
    /** 搜索按钮操作 */
    handleQuery() {
      this.queryParams.pageNum = 1;
      this.getMedicineList();
    },
    /** 重置按钮操作 */
    resetQuery() {
      this.resetForm("rQueryForm");
      this.queryParams.pageNum = 1;
      this.getMedicineList();
    },
    /** 查询药品列表 */
    getMedicineList(){
      listHisMedicine(this.queryParams).then(response=>{
        this.loading=false;
        this.total = response.total;
        this.medicines=response.rows;
      })
    },
    /** 条件查询药品列表 */
    medicinesSelect(){
      this.getMedicineList();
    },
  }
};
</script>
