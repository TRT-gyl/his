<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">
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
          @click="handleAdd"
          v-hasPermi="['his:purchase:add']"
        >新增药品</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="danger"
          icon="el-icon-edit"
          size="mini"
          @click="auditPurchase"
        >提交审核</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-check"
          size="mini"
          @click="auditHold"
        >暂存</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="medcs" >
      <el-table-column label="药品ID" align="center" prop="medicineId" />
      <el-table-column label="药品名称" align="center" prop="medicineName" />
      <el-table-column label="生产厂家" align="center" prop="factory.factoryName" />
      <el-table-column label="数量" align="center" prop="" width="150">
        <template slot-scope="scope">
        <el-input-number v-model="initParams.purchases[scope.$index].amount"   size="small" @change="totalMoney(scope)"/></template>
      </el-table-column>
      <el-table-column label="单位" align="center" prop="unit" />
      <el-table-column label="批发价" align="center" prop="" width="150" >
        <template slot-scope="scope">
            <el-input-number v-model="initParams.purchases[scope.$index].wholesale"    size="small" :precision="2" :min="0" @change="totalMoney(scope)"/>
        </template>
      </el-table-column >
      <el-table-column label="批发额" align="center" prop="" width="80px">
        <template slot-scope="scope">
          <el-input
            v-model="initParams.purchases[scope.$index].totalMoney" disabled
          />
        </template>
      </el-table-column>
      <el-table-column label="批次号" align="center" prop=""  width="80px">
        <template slot-scope="scope">
          <el-input
            v-model="initParams.purchases[scope.$index].batch"
          />
        </template>
      </el-table-column>
      <el-table-column label="备注" align="center" prop="" width="150px" >
        <template slot-scope="scope">
          <el-input
            v-model="initParams.purchases[scope.$index].remark"
            clearable
          />
        </template>
      </el-table-column>
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="el-icon-delete"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope)"
            v-hasPermi="['his:purchase:remove']"
          >删除</el-button>
        </template>
      </el-table-column>
    </el-table>


    <!-- 添加或修改药品采购对话框 -->
    <el-dialog :title="title"   center :visible.sync="open" width="900px" append-to-body>
      <el-form ref="medicineable" :model="form" :rules="rules" label-width="80px"   >
        <el-row>
          <el-col :span="7">
            <el-form-item label="关键字" prop="keyword" >
              <el-input v-model="queryParams.keyword" placeholder="请输入关键字"   />
            </el-form-item>
          </el-col>
          <el-col :span="7">
            <el-form-item label="药品类型" prop="category">
              <el-select v-model="queryParams.medicineType" placeholder="请选择药品类型" clearable >
                <el-option
                  v-for="sup in medicineTypes"
                  :key="sup"
                  :value="sup"
                />
              </el-select>
            </el-form-item>
          </el-col>
          <el-col :span="7">
            <el-form-item label="处方类型" prop="category">
              <el-select v-model="queryParams.prescribe" placeholder="请选择供应商" clearable >
                <el-option
                  v-for="sup in prescribes"
                  :key="sup"
                  :value="sup"
                />
              </el-select>
            </el-form-item>
          </el-col>
          <el-col :span="2">
              <el-button type="primary" @click="medicinesSelect" icon="el-icon">查 询</el-button>
          </el-col>
        </el-row>
        <el-table v-loading="loading" border :data="medicines" @selection-change="handleSelectionChange">
          <el-table-column type="selection" width="55" align="center"  />
          <el-table-column label="药品ID" align="center" prop="medicineId" />
          <el-table-column label="药品名称" align="center" prop="medicineName" />
          <el-table-column label="药品编码" align="center" prop="medicineNo" />
          <el-table-column label="生产厂家" align="center" prop="factory.factoryName" />
          <el-table-column label="药品类型" align="center" prop="medicineType" />
          <el-table-column label="处方类型" align="center" prop="prescribe" />
          <el-table-column label="关键字" align="center" prop="keyword" />
        </el-table>
      </el-form>
      <pagination
        v-show="total>0"
        :total="total"
        :page.sync="queryParams.pageNum"
        :limit.sync="queryParams.pageSize"
        @pagination="getMedicineList"
      />
      <div slot="footer" class="dialog-footer">
        <el-button @click="cancel">确 定</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
import { listPurchase, getPurchase, delPurchase, addPurchase, updatePurchase } from "@/api/his/purchase";
import {listHisSupplie,listHisMedicine } from "@/api/his/warehousing";

export default {
  name: "Purchase",
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
      //供应商集合
      suppliers: [],
      //药品集合
      medicines: [],
      //需要增加到订单的药品集合
      medcs: [],
      //订购的药品id集合
      mId: [],
      //采购入库初始的参数
      initParams:{
        id: null,
        billsid: null,
        suppliersId: null,
        totalMoney: 0,
        status: null,
        proposer: null,
        operator: null,
        storageTime: null,
        createBy: null,
        createTime: null,
        updateBy: null,
        updateTime: null,
        remark: null,
        purchases: null
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
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        keyword: null,
        medicineType: null,
        prescribe: null
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
        drugId: [
          { required: true, message: "药品表外键不能为空", trigger: "blur" }
        ],
        pwId: [
          { required: true, message: "采购入库表外键不能为空", trigger: "blur" }
        ],
        amount: [
          { required: true, message: "数量不能为空", trigger: "blur" }
        ],
        wholesale: [
          { required: true, message: "批发单价不能为空", trigger: "blur" }
        ],
        totalMoney: [
          { required: true, message: "批发总金额不能为空", trigger: "blur" }
        ],
        batch: [
          { required: true, message: "批次号不能为空", trigger: "blur" }
        ],
      }
    };
  },
  created() {
    this.initParams.billsid=this.getBillsid();
    this.initParams.createBy=this.$store.state.user.name;
    this.initParams.createTime=new Date().toISOString().slice(0, 19).replace('T', ' ');
    listHisSupplie().then(response=>{
      this.suppliers=response.data;
    });
    this.getList();
  },
  methods: {
    //提交审核
    auditPurchase(){
        this.initParams.status='1';
        this.initParams.proposer=this.$store.state.user.name;
        addPurchase(this.initParams).then(response => {
          this.$alert("提交审核成功","入库",{
            confirmButtonText: '确定',
            type: 'success'
          }).then(r=>{
            this.$store.dispatch("tagsView/delView", this.$route);
            this.$router.push("/his/warehousing/list");
          });
      });
    },
    //暂存
    auditHold(){
      this.initParams.status='0';
      this.initParams.proposer=this.$store.state.user.name;
      addPurchase(this.initParams).then(response => {
        this.$alert("暂存成功","入库",{
          confirmButtonText: '确定',
          type: 'success'
        }).then(r=>{
          this.$store.dispatch("tagsView/delView", this.$route);
          this.$router.push("/his/warehousing/list");
        });
      });
    },
    //计算单个药品种类的批发总价并计算全部总价
    totalMoney(scope){
      this.initParams.totalMoney=0;
      this.initParams.purchases[scope.$index].totalMoney=this.initParams.purchases[scope.$index].amount*this.initParams.purchases[scope.$index].wholesale;
      for (let purs of this.initParams.purchases) {
        this.initParams.totalMoney+=purs.totalMoney;
      }
    },
    /** 查询药品采购列表 */
    getList() {
      this.loading = true;
      listPurchase(this.queryParams).then(response => {
        this.purchaseList = response.rows;
        this.total = response.total;
        this.loading = false;
      });
    },
    // 取消按钮
    cancel() {
      this.open = false;
      this.queryParams.keyword=null;
      this.queryParams.pageNum=1;
      this.queryParams.pageSize=10;

    },
    // 表单重置
    reset() {
      this.form = {
        keyword: null
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
      console.log(this.initParams.suppliersId);


    },
    // 多选框选中数据
    handleSelectionChange(selection) {
      this.medcs=selection;
      this.initParams.purchases=[];
      for (let selectionElement of selection) {
          let ppp={
            drugId: 0,
            amount: 0,
            wholesale: 0,
            batch: null,
            totalMoney: 0,
            remark: null
          };
          ppp.drugId=selectionElement.medicineId;
          this.initParams.purchases.push(ppp);
      }
    },
    /** 新增按钮操作 */
    handleAdd() {
      if(this.initParams.suppliersId==null){
        this.$modal.msgWarning("请先选择供应商");
        return;
      }
      this.open = true;
      this.title = "添加药品信息";
      this.getMedicineList();

    },
    handleAdd2() {
      this.open = true;
      this.title = "添加药品信息";
      if(this.medicines.length>0){
        return;
      }
      this.getMedicineList();

    },
    /** 查询药品列表 */
    getMedicineList(){
      listHisMedicine(this.queryParams).then(response=>{
        this.total = response.total;
        this.medicines=response.rows;
      })
    },
    /** 条件查询药品列表 */
    medicinesSelect(){
      this.getMedicineList();
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const id = row.id || this.ids
      getPurchase(id).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改药品采购";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.id != null) {
            updatePurchase(this.form).then(response => {
              this.$modal.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addPurchase(this.form).then(response => {
              this.$modal.msgSuccess("新增成功");
              this.open = false;
              this.getList();
            });
          }
        }
      });
    },
    /** 删除按钮操作 */
    handleDelete(scope) {
      let row=scope.row;
      const ids = scope.$index;
      this.$modal.confirm('是否确认删除药品名为' + row.medicineName + '的数据项？').then(r=>{
        this.initParams.purchases.splice(ids,1);
        let resu=this.medcs.splice(ids,1);
        if(resu.length==1){
          this.$message.success("删除成功");
        }else{
          this.$message.error("删除失败");
        }
      })
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('his/purchase/export', {
        ...this.queryParams
      }, `purchase_${new Date().getTime()}.xlsx`)
    },
    /**获取单据ID*/
    getBillsid(){
      let id="CG"+Date.now();
      let i=0;
      while(i<=3){
        i++;
        id=id+Math.floor(Math.random()*10);
      }
      return id;
    }
  }
};
</script>
