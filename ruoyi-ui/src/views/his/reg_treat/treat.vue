<template>
    <div>
        <el-row>
            <el-col :span="6">
                <el-card :body-style="{ margin: '10px',padding: '5px' }">
                    <el-form ref="patientform" :model="patientform" label-width="80px" label-position="left">
                        <el-form-item label="患者姓名">
                            <el-input v-model="patientform.patientName" disabled>
                                <el-button slot="append" icon="el-icon-s-custom" @click="checkpatient"></el-button>
                            </el-input>
                        </el-form-item>
                        <el-form-item label="身份证号">
                            <el-input v-model="patientform.idCard" disabled></el-input>
                        </el-form-item>
                        <el-form-item label="患者电话">
                            <el-input v-model="patientform.tel" disabled></el-input>
                        </el-form-item>
                        <el-form-item label="性别" prop="gender">
                            <el-radio-group v-model="patientform.gender">
                                <el-radio label="0">女</el-radio>
                                <el-radio label="1">男</el-radio>
                                <el-radio label="2">未知</el-radio>
                            </el-radio-group>
                        </el-form-item>
                        <el-form-item label="出生日期" prop="patientDate">
                            <el-date-picker clearable v-model="patientform.patientDate" type="date"
                                value-format="yyyy-MM-dd" placeholder="请选择出生日期">
                            </el-date-picker>
                        </el-form-item>
                        <el-form-item label="患者年龄" prop="age">
                            <el-input v-model="patientform.age" placeholder="请输入患者年龄" />
                        </el-form-item>
                        <el-form-item label="地址信息" prop="address">
                            <el-input v-model="patientform.address" placeholder="请输入地址信息" type="textarea" />
                        </el-form-item>
                        <el-form-item label="过敏史" prop="allergy">
                            <el-input v-model="patientform.allergy" placeholder="请输入过敏史" type="textarea" />
                        </el-form-item>
                        <el-form-item>
                            <el-button type="primary" @click="update">更新患者信息</el-button>
                        </el-form-item>
                    </el-form>
                </el-card>
            </el-col>
            <el-col :span="18">
                <el-card>
                    <el-form :inline="true" size="mini">
                        <el-form-item label="挂号编号：">{{registration.registrationNo}}</el-form-item>
                        <el-form-item label="病例编号：">{{treatform.treatNo}}</el-form-item>
                        <el-form-item>
                            <el-button type="primary" @click="add">保存病例</el-button>
                            <el-button type="success" @click="updateReg">就诊完成</el-button>
                        </el-form-item>
                    </el-form>
                </el-card>
            </el-col>
            <el-col :span="18">
                <el-card :body-style="{ margin: '5px',padding: '5px' }">
                    <el-tabs v-model="activeName2" type="border-card">
                        <el-tab-pane label="病例" name="first">
                            <el-form ref="treatform" :model="treatform" label-width="80px" size="mini"
                                label-position="left">
                                <el-form-item label="发病日期" prop="treatDate">
                                    <el-date-picker clearable v-model="treatform.treatDate" type="date"
                                        value-format="yyyy-MM-dd" placeholder="请选择发病日期">
                                    </el-date-picker>
                                </el-form-item>
                                <el-form-item label="是否传染" prop="treatContagion">
                                    <el-select v-model="treatform.treatContagion" placeholder="请选择是否传染" clearable>
                                        <el-option v-for="dict in dict.type.his_treat_contagion" :key="dict.value"
                                            :label="dict.label" :value="dict.value" />
                                    </el-select>
                                </el-form-item>
                                <el-form-item label="主诉" prop="treatComplaint">
                                    <el-input v-model="treatform.treatComplaint" placeholder="请输入主诉" type="textarea"
                                        maxlength="100" show-word-limit />
                                </el-form-item>
                                <el-form-item label="诊断信息" prop="info">
                                    <el-input v-model="treatform.treatInfo" placeholder="请输入诊断信息" type="textarea"
                                        maxlength="100" show-word-limit />
                                </el-form-item>
                                <el-form-item label="医生建议" prop="treatAdvice">
                                    <el-input v-model="treatform.treatAdvice" placeholder="请输入医生建议" type="textarea"
                                        maxlength="100" show-word-limit />
                                </el-form-item>
                                <el-form-item label="备注" prop="remark">
                                    <el-input v-model="treatform.remark" placeholder="请输入备注" type="textarea"
                                        maxlength="250" show-word-limit />
                                </el-form-item>
                            </el-form>
                        </el-tab-pane>
                        <el-tab-pane label="处方" name="second">
                            <el-button type="success" @click="addmedicine">添加用药处方</el-button>
                            <el-button type="success" @click="addcheck">添加检查处方</el-button>
                        </el-tab-pane>
                    </el-tabs>
                </el-card>
            </el-col>
        </el-row>
        <!-- 选择挂号患者窗口-->
        <el-dialog title="选择挂号患者" :visible.sync="dialogTableVisible">
            <el-tabs v-model="activeName1" type="card" @tab-click="tabclick">
                <el-tab-pane label="待就诊列表" name="one">
                    <el-table :data="registrationList">
                        <el-table-column label="患者姓名" align="center" prop="patientName" />
                        <el-table-column label="流水编号" align="center" prop="registrationId" />
                        <el-table-column label="挂号类型" align="center" prop="scheduleType" />
                        <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
                            <template slot-scope="scope">
                                <el-button size="mini" type="success" icon="el-icon-check"
                                    @click="handleUpdate(scope.row)">接诊
                                </el-button>
                            </template>
                        </el-table-column>
                    </el-table>
                    <pagination v-show="total>0" :total="total" :page.sync="queryParams.pageNum"
                        :limit.sync="queryParams.pageSize" @pagination="getList" />
                </el-tab-pane>
                <el-tab-pane label="就诊中列表" name="weo">
                    <el-table :data="registrationList">
                        <el-table-column label="患者姓名" align="center" prop="patientName" />
                        <el-table-column label="流水编号" align="center" prop="registrationId" />
                        <el-table-column label="挂号类型" align="center" prop="scheduleType" />
                        <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
                            <template slot-scope="scope">
                                <el-button size="mini" type="success" icon="el-icon-check"
                                    @click="handleUpdate(scope.row)">接诊
                                </el-button>
                            </template>
                        </el-table-column>
                    </el-table>
                    <pagination v-show="total>0" :total="total" :page.sync="queryParams.pageNum"
                        :limit.sync="queryParams.pageSize" @pagination="getList" />
                </el-tab-pane>
                <el-tab-pane label="就诊完成列表" name="three">
                    <el-table :data="registrationList">
                        <el-table-column label="患者姓名" align="center" prop="patientName" />
                        <el-table-column label="流水编号" align="center" prop="registrationId" />
                        <el-table-column label="挂号类型" align="center" prop="scheduleType" />
                    </el-table>
                    <pagination v-show="total>0" :total="total" :page.sync="queryParams.pageNum"
                        :limit.sync="queryParams.pageSize" @pagination="getList" />
                </el-tab-pane>
            </el-tabs>
        </el-dialog>
    </div>
</template>
  <script>
    import { getPatient, updatePatient } from "@/api/his/patient";
    import { getRegistration,getRegAndTreat,updateRegistration} from "@/api/his/registration";
    import { listTreat, addTreat, updateTreat } from "@/api/his/treat";
  export default {
    dicts: ['his_treat_contagion'],
    data() {
      return {
        value:'',
        total: 0,
        dialogTableVisible:false,
        queryParams:{
          pageNum: 1,
          pageSize: 10,
          registrationStatus: 1,
        },
        // 挂号列表表格数据
        registrationList: [],
        //患者表单数据
        patientform: {
          patientName: '',
          idCard: '',
          tel: '',
          gender: '',
          patientDate: '',
          age:'',
          address: '',
          allergy: '',
          patientId:'',
        },
        registration:{
            registrationId:"",//挂号主键
            registrationStatus:"",//挂号状态
            registrationNo:"",//挂号编号
        },
        //就诊表单数据
        treatform:{
            treatRegistrationId:null,
            treatDate:null,
            treatContagion:null,
            treatComplaint:null,
            treatInfo:null,
            treatAdvice:null,
            remark:null,
            treatNo:null
        },
        activeName1: 'one', //默认显示第一页
        activeName2: 'first' //默认显示第一页
      };
    },
    created() {
      },
    methods: {
    /**重置 */
    reset(){
        this.treatform={
            treatRegistrationId:null,
            treatDate:null,
            treatContagion:null,
            treatComplaint:null,
            treatInfo:null,
            treatAdvice:null,
            remark:null,
            treatNo:null
        };
        this.patientform={
          patientName: '',
          idCard: '',
          tel: '',
          gender: '',
          patientDate: '',
          age:'',
          address: '',
          allergy: '',
          patientId:'',
        };
        this.registration={
            registrationId:"",//挂号主键
            registrationStatus:"",//挂号状态
            registrationNo:"",//挂号编号
        };
    },
    /**查询列表 */
      getList(){
        listTreat(this.queryParams).then(r => {
            this.registrationList = r.rows;
        });
      },
    //选择患者按钮操作  
      checkpatient(){
        this.dialogTableVisible=true;
        this.getList();
      },
    /** 接诊按钮操作 */
    handleUpdate(row) {
        this.reset();
        this.dialogTableVisible=false;
        this.registration.registrationId=row.registrationId;
        this.registration.registrationStatus=row.registrationStatus;
        getRegistration(this.registration.registrationId).then(r1=>{//加载挂号ID,判断挂号状态加载不同的信息
            this.registration.registrationNo=r1.data.registrationNo;
            if(r1.code==200&&this.registration.registrationStatus==1){//加载待就诊患者信息
                this.patientform.patientId=r1.data.registrationPatientId;
                getPatient(r1.data.registrationPatientId).then(r2=>{ 
                    if(r2.code==200){
                        this.patientform=r2.data;
                    }
                });
            }else if(r1.code==200&&this.registration.registrationStatus==4){//加载就诊中患者信息
                this.patientform.patientId=r1.data.registrationPatientId;
                getPatient(r1.data.registrationPatientId).then(r2=>{
                    if(r2.code==200){
                        this.patientform=r2.data;
                        getRegAndTreat(this.registration.registrationId).then(r=>{//加载病例信息
                             if(r.code=200){
                                this.treatform=r.data
                             }
                        })
                    }
                });

            };
        });
    },
    /**更新患者信息按钮 */   
    update(){
        updatePatient(this.patientform).then(r=>{
           if(r.code=200){
            this.$modal.msgSuccess("更新成功");
           }
        })
    },
    /**保存病例按钮 */
    add(){
        console.log(this.patientform.patientId)
        if(this.patientform.patientId==''){//无患者信息
            this.$message.error("请选择待就诊患者")
        }else if(this.treatform.treatNo==null){//无病例单号，增加病例
            this.treatform.treatRegistrationId=this.registration.registrationId;
            console.log(this.treatform)
            addTreat(this.treatform).then(r=>{
                if(r.code=200){
                    this.treatform.treatNo=r.data;
                    this.$modal.msgSuccess("病历保存成功");
                }
            });
        }else {//有患者信息，有病例单号，修改病例
            updateTreat(this.treatform).then(r=>{
                if(r.code=200){
                    this.$modal.msgSuccess("病历保存成功");
                }
            })
        }
    },
    /**就诊完成按钮 */
    updateReg(){
        this.add();
        this.registration.registrationStatus=2;
        updateRegistration(this.registration).then(r=>{
            if(r.code==200){
                this.$modal.msgSuccess("就诊完成");
                this.reset();//清空
            }
        })

    },
    //选择挂号患者窗口的事件
    tabclick(tab, event) {
        if (tab.index == 0) {
        this.queryParams.registrationStatus=1;
      } else if (tab.index == 1) {  
        this.queryParams.registrationStatus=4;
      } else {
        this.queryParams.registrationStatus=2;
      };
      this.getList();
    },
   //addcheck
   addcheck(){
        
   },   
   //addmedicine   
   addmedicine(){

   },    
 },
}
  </script>
  
  