<template>
  <div class="app-container">
    <el-card class="box-card" style="margin: 10px">
      <el-form
        :model="queryParams"
        ref="queryForm"
        size="small"
        :inline="true"
        v-show="showSearch"
        label-width="68px"
      >
        <el-form-item label="挂号单ID" prop="registrationId">
          <el-input
            placeholder="请输入挂号单"
            v-model="queryParams.registrationId"
            clearable
            @keyup.enter.native="handleQuery"
          ></el-input>
        </el-form-item>

        <el-button
          type="primary"
          icon="el-icon-search"
          size="mini"
          @click="handleQuery"
          >查询</el-button
        >
        <el-button type="warning" icon="el-icon-search" size="mini"
          >读取IC卡</el-button
        >
      </el-form>
    </el-card>

    <div v-show="xianshi">
      <el-card class="box-card" style="margin: 10px">
        <el-descriptions>
          <el-descriptions-item label="挂号单号">{{registration.registrationId}}</el-descriptions-item>
          <el-descriptions-item label="医生姓名">{{doctor.doctName}}</el-descriptions-item>
          <el-descriptions-item label="科室名称">{{dept.deptName}}</el-descriptions-item>
          <el-descriptions-item label="患者姓名">{{patient.patientName}}</el-descriptions-item>
          <el-descriptions-item label="就诊时间">{{registration.registrationNo}}</el-descriptions-item>
          <el-descriptions-item label="主诉">{{treat.treatComplaint}}</el-descriptions-item>
          <el-descriptions-item label="诊断信息">{{treat.treatInfo}}</el-descriptions-item>
        </el-descriptions>
      </el-card>

      <el-card class="box-card" style="margin: 10px">
        <el-row>
          <!-- <el-button type="primary" icon="el-icon-shopping-cart-full"
            >全选</el-button
          >
          <el-button type="success" icon="el-icon-shopping-cart-2"
            >取消全选</el-button
          > -->
          <el-button type="danger" icon="el-icon-files" @click="send">确定发药</el-button>

        </el-row>
      </el-card>

      <el-card class="box-card" style="margin: 10px">
        <el-row> 【检查处方】【1】【处方总额】:人民币100 </el-row>
        <el-row> 【用药处方】【2】【处方总额】:人民币<span>{{sumAmout.toFixed(1)}}</span> </el-row>


    <el-table v-loading="loading" :data="prescriptionList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="序号" align="center" type="index" :index="indexMethod"/>
      <el-table-column label="药品名称" align="center" prop="medicineId" />
      <el-table-column label="数量" align="center" prop="quantity" />
      <!-- <el-table-column label="单价" align="center" prop="deptNum" /> -->
      <el-table-column label="金额（元）" align="center" prop="amount" />
      <el-table-column label="服用备注" align="center" prop="notes" />
      <el-table-column label="支付状态" align="center" prop="state">
        <template slot-scope="scope">
          <dict-tag :options="dict.type.his_prescription_state" :value="scope.row.state" />
        </template>
      </el-table-column>
    </el-table>

      </el-card>
    </div>
    <!-- 如果没有输入订单号或订单号错误，显示页面 -->
    <div v-show="enrro">
      <h2 style="color: red; text-align: center">请输入正确的挂号单号ID查询</h2>
    </div>

  </div>
</template>

<script>
//支付组件


import { getRegistration } from "@/api/his/registration";
import { getPatient } from "@/api/his/patient";
import { getTreatByRegId } from "@/api/his/treat";
import { getScheduleById } from "@/api/his/schedule";
import { getDoctor } from "@/api/his/doctor";
import { getDept } from "@/api/his/dept";
import { listPrescription,getPrescription } from "@/api/his/prescription";
export default {
  name: "Prescription",
  dicts: ['his_prescription_state'],
  //计算药品总金额
  computed:{
    sumAmout(){
      return this.prescriptionList
      .map(row=>row.amount)
      .reduce((acc,cur)=>parseFloat(cur)+acc,0);
    }
  },
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
     // total: 0,
      buy_code: false,
      xianshi: false,
      enrro: true,

      registration: [], //挂号
      patient: [],
      treat: [],
      schedule:[],
      dept:[],
      doctor:[],
      prescriptionList:[],

      //选中行的状态（多个，这只是测试）
      PState:1,
      // 查询参数
      queryParams: {
        registrationId: null,
      },
      //处方药品查询参数
      queryParamsP:{
         pageNum: 2,

        treatId:null,
      },
      // 表单参数
      form: {},
    };
  },
  methods: {
     /** 状态错误提示 */
     open4() {
        this.$message.error('发药错误，包含未缴费项或已退费项');
      },
    /** 根据挂号ID查询挂号详情 */
    getRegistrations() {
      getRegistration(this.queryParams.registrationId).then((response) => {
        console.log(response);
        if (response.data != null) {
          //如果返回成功,显示具体窗口
          this.xianshi = true;
          this.enrro = false;
          this.registration = response.data;


          //console.log(this.registration);
          /** 根据患者ID查询患者详情 */
          getPatient(this.registration.registrationPatientId).then(
            (response1) => {
              this.patient = response1.data;
              console.log(this.patient);
            }
          ),
            /** 根据挂号Id查询就诊详情 */
            getTreatByRegId(this.queryParams.registrationId).then(
              (response2) => {
                this.treat = response2.data;
                this.queryParamsP.treatId=this.treat.treatId;

                /** 根据就诊ID查询处方药品列表 */
              listPrescription(this.queryParamsP).then((responseP=>{
              this.prescriptionList=responseP.rows;
              this.total = responseP.total;
              this.loading = false;
          }))
              }
            ),
             /** 根据排班Id查询排班详情 */
            getScheduleById(this.registration.registrationSchedule).then(
              (response3) => {
                this.doctor.doctId = response3.data.doctId;
                /** 根据医生Id查医生 */
            getDoctor(this.doctor.doctId).then(
              (response4)=>{
                this.doctor=response4.data;
                console.log(this.doctor)
                getDept(this.doctor.deptId).then((response5)=>{
                  this.dept=response5.data
                })
              }
            )
              }
            );

        } else {
          this.xianshi = false;
          this.enrro = true;
          this.registration.registrationId = 0;
        }
      });
    },

    getPatient() {
      getPatient(this.registration.p);
    },
    handleChange(value) {},
    /** 搜索按钮操作 */
    handleQuery() {
      //this.queryParamsP.pageNum = 1;
      this.getRegistrations();
     // this.listPrescriptions();

    },
     /** 发药按钮操作 */
    send(){
      //只有状态为已支付才能发药
    if(this.PState==1){
      this.open4();
    }
    },
     // 多选框选中数据
    handleSelectionChange(selection) {
      this.ids = selection.map(item => item.inspectId)
      this.single = selection.length!==1
      this.multiple = !selection.length
    },
    indexMethod(index) {
      return index + 1;
    },
    toggleSelection(rows) {
      if (rows) {
        rows.forEach((row) => {
          this.$refs.multipleTable.toggleRowSelection(row);
        });
      } else {
        this.$refs.multipleTable.clearSelection();
      }
    },
    handleSelectionChange(val) {
      this.multipleSelection = val;
    },

  },
};
</script>




