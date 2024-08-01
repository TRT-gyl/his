<template>
  <div>
    <el-card class="box-card" style="margin: 10px">
      <el-form :inline="true" size="mini">
        <el-form-item label="身份证号:">
          <el-input v-model="idCard" placeholder="请输入身份证号" clearable />
        </el-form-item>
        <el-form-item>
          <el-button type="primary" icon="el-icon-search" @click="getPat">加载身份证号</el-button>
        </el-form-item>
      </el-form>
    </el-card>
    <el-card class="box-card" style="margin: 10px">
      <el-form :inline="true" :model="patientform" size="mini" :rules="rules">
        <el-row>
          <el-col>
            <el-form-item label="身份证号" prop="idCard">
              <el-input v-model="patientform.idCard" placeholder="请输入患者身份证" />
            </el-form-item>
            <el-form-item label="患者姓名" prop="patientName">
              <el-input v-model="patientform.patientName" placeholder="请输入患者姓名" />
            </el-form-item>
            <el-form-item label="患者电话" prop="tel">
              <el-input v-model="patientform.tel" placeholder="请输入患者电话" />
            </el-form-item>
            <el-form-item label="性别" prop="gender">
              <el-radio-group v-model="patientform.gender">
                <el-radio label="0">女</el-radio>
                <el-radio label="1">男</el-radio>
                <el-radio label="2">未知</el-radio>
              </el-radio-group>
            </el-form-item>
            <el-form-item label="出生日期" prop="patientDate">
              <el-date-picker clearable v-model="patientform.patientDate" type="date" value-format="yyyy-MM-dd"
                placeholder="请选择出生日期">
              </el-date-picker>
            </el-form-item>
          </el-col>
        </el-row>
        <el-row>
          <el-col style="margin: 5px" :span="24">
            <el-form-item label="患者年龄" prop="age">
              <el-input v-model="patientform.age" placeholder="请输入患者年龄" />
            </el-form-item>
            <el-form-item label="地址信息" prop="address">
              <el-input v-model="patientform.address" placeholder="请输入地址信息" />
            </el-form-item>
            <el-form-item>
              <el-button type="warning" icon="el-icon-search" @click="add">添加患者信息</el-button>
            </el-form-item>
          </el-col>
        </el-row>
      </el-form>
    </el-card>
    <el-card>
      <el-row>
        <el-form :inline="true">
          <el-form-item>
            <el-checkbox-button v-model="checked" @click="addBook">是否需要病历本</el-checkbox-button>
          </el-form-item>
          <el-form-item>
            <el-col :span="10"></el-col>
            ￥{{regCost}} <el-button type="danger" @click="addReg">挂号收费</el-button>
          </el-form-item>
        </el-form>
      </el-row>


    </el-card>
    <el-card class="box-card" style="margin: 10px">
      <div>
        <el-form :model="queryParams" :inline="true" size="mini" ref="queryParams">
          <el-form-item label="科室&医生" prop="doctId">
            <el-cascader :props="{ value: 'id',label: 'name',children: 'children',emitPath:false}"
              v-model="queryParams.doctId" :options="depts" @change="handleChange" clearable placeholder="请选择科室&医生">
            </el-cascader>
          </el-form-item>
          <el-form-item label=" 挂号时段" prop="scheduleTime">
            <el-select v-model="queryParams.scheduleTime" placeholder="请选择挂号时段" clearable>
              <el-option value="0" label="上午" />
              <el-option value="1" label="下午" />
              <el-option value="2" label="晚上" />
            </el-select>
          </el-form-item>
          <el-form-item label=" 挂号类型" prop="scheduleType">
            <el-select placeholder="请选择挂号类型" v-model="queryParams.scheduleType">
              <el-option value="门诊" label="门诊" />
              <el-option value="急诊" label="急诊" />
            </el-select>
          </el-form-item>
          <el-form-item label=" 挂号日期" prop="scheduleDate">
            <el-date-picker type="date" placeholder="挂号日期" v-model="queryParams.scheduleDate"
              :default-value="timeDefaultShow" value-format="yyyy-MM-dd" format="yyyy-MM-dd"
              :picker-options="pickerOptions">
            </el-date-picker>
          </el-form-item>
          <el-form-item>
            <el-button type="primary" icon="el-icon-search" @click="handleQuery">搜索</el-button>
            <el-button icon="el-icon-refresh" @click="resetQuery">重置</el-button>
          </el-form-item>
        </el-form>
      </div>
      <el-table :data="dataList" border style="width: 100%" highlight-current-row @current-change="handleCurrentChange">
        <el-table-column prop="scheduleId" label="挂号ID" width="180" align="center">
        </el-table-column>
        <el-table-column prop="deptName" label="科室名称" width="180" align="center">
        </el-table-column>
        <el-table-column prop="doctName" label="医生姓名" align="center">
        </el-table-column>
        <el-table-column prop="scheduleTime" label="挂号时段" align="center">
          <template slot-scope="scope">
            <dict-tag :options="dict.type.his_schedule_time" :value="scope.row.scheduleTime" />
          </template>
        </el-table-column>
        <el-table-column prop="scheduleType" label="挂号类型" align="center">
        </el-table-column>
        <el-table-column prop="scheduleNums" label="当前号数" align="center">
        </el-table-column>
      </el-table>
      <pagination v-show="total > 0" :total="total" :page.sync="queryParams.pageNum" :limit.sync="queryParams.pageSize"
        @pagination="getList" />
    </el-card>
  </div>
</template>
<script>
  import {  addPatient,getPatientByidCard } from "@/api/his/patient";
  import {addRegistration,listDept,listRegable } from "@/api/his/registration";
export default {
  dicts: ['his_schedule_time'],
  data() {
    return {
    //是否需要病历本，默认不需要
      checked: false,
    //挂号费用
      regCost:null,
    //禁用今日之前的日期
      pickerOptions: {
          disabledDate(date) {
            let curDate = new Date();
            return date<new Date(curDate.getTime() - 24*60*60*1000);
          }},
     // 总条数
      total: 0,
      timeDefaultShow :"",
      //级联下拉框初始化数据
      depts:[],
      //新增患者表单数据
      patientform: {},
      // 查询可挂号列表参数
      queryParams: {
          pageNum: 1,
          pageSize: 10,
          doctId: null,
          scheduleType:null,
          scheduleTime:null,
          scheduleDate:null,
        },
      //增加挂号参数
      addRegParams:{
        registrationType:null,
        registrationPatientId:null,
        registrationSchedule:null,
      },
      idCard:"",//用于加载身份证
      // 表单校验
      rules: {
        patientName: [
          { required: true, message: "患者姓名不能为空", trigger: "blur" }
        ],
        tel: [
          { required: true, message: "患者电话不能为空", trigger: "blur" }
        ],
        idCard: [
          { required: true, message: "患者身份证不能为空", trigger: "blur" }
        ],
      },
      //可挂号表格数据
      dataList:[],
    };
  },
  created() {
      this.init();
      this.getList();
    },
  methods: {
    handleChange(value) {
      console.log(value);
    },
    //加载身份证号按钮
    getPat(){
      getPatientByidCard(this.idCard).then(r => {
        console.log("加载身份证号按钮的回调",r)
        if(r.data==null){
          this.$modal.msgError("该患者不存在，请增加患者信息");
        }else{
        this.patientform=r.data;
        this.addRegParams.registrationPatientId=r.data.patientId;
        }
      });
    },
    //添加患者信息
    add() {
      addPatient(this.patientform).then(r => {
        if(r.data!=null){
          this.$modal.msgSuccess("新增成功");
          this.addRegParams.registrationPatientId=r.data;
        }else{
          this.$message.error("该患者已存在，无需添加患者信息")
        }
      })
    },
    init(){
      listDept().then(r=>{
         this.depts=r.data
      });
      this.timeDefaultShow = new Date();//挂号日期默认当天
      this.queryParams.scheduleDate=new Date();
      console.log("今日日期：",this.timeDefaultShow)
    },
    /** 搜索按钮操作 */
     handleQuery() {
        this.queryParams.pageNum = 1;
        console.log("搜索的参数",this.queryParams)
        this.getList();
      },
      /** 重置按钮操作 */
      resetQuery() {
        this.resetForm("queryParams");
         this.handleQuery();
        console.log("重置后的参数",this.queryParams)
      },
      getList() {//获取可挂号列表
        listRegable(this.queryParams).then(r => {
                  if (r.code == 200 ) {
                      this.dataList = r.rows;
                      this.total = r.total;
                  } 
              });
          },
      //表格单选
     handleCurrentChange(val) {
        this.addRegParams.registrationSchedule=val.scheduleId;
        if(this.checked&&val.scheduleType=="门诊"){
            this.addRegParams.registrationType=2
            this.regCost=8
        }else if(this.checked&&val.scheduleType=="急诊"){
          this.addRegParams.registrationType=4
          this.regCost=12
        }else if(!this.checked&&val.scheduleType=="急诊"){
          this.addRegParams.registrationType=3
          this.regCost=10
        }else if(!this.checked&&val.scheduleType=="门诊"){
          this.addRegParams.registrationType=1
          this.regCost=6
        };
        console.log(this.addRegParams)
      },
      //挂号按钮操作
      addReg(){
        addRegistration(this.addRegParams).then(r=>{
          console.log("挂号回调响应",r)
          let a=""
          if(r.code==200&&r.data!=0){
            a="挂号成功,挂号订单编号为："+r.data;
            this.$modal.msgSuccess(a); 
          }else if(r.code==200&&r.data==0){
            this.$modal.msgWarning("当前号源已挂完，请重新挂号");
          }
          
        })
      },
      addBook(){
        // if(this.checked&&this.addRegParams.registrationType==2){
        //     this.regCost=8
        // }else if(this.checked&&this.addRegParams.registrationType==4){
        //   this.regCost=12
        // }else if(!this.checked&&this.addRegParams.registrationType==3){
        //   this.regCost=10
        // }else if(!this.checked&&this.addRegParams.registrationType==1){
        //   this.regCost=6
        // };
      }
  },


}
</script>

