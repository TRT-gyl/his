<template>
    <div>
        <el-card style="margin: 5px">
            <el-row :gutter="0.5" type="flex" size="mini">
                <el-col :span="1">科室：</el-col>
                <el-col :span="3">
                    <el-input v-model="hisSchedule.deptName" placeholder="请输入所属科室">
                    </el-input>
                </el-col>
                <el-col :span="1">医生：</el-col>
                <el-col :span="3">
                    <el-input v-model="hisSchedule.doctName" placeholder="请输入医生名称">
                    </el-input>
                </el-col>
                <el-col :span="5">
                    <el-button type="primary" @click="submitForm">搜索</el-button>
                    <el-button @click="resetForm">重置</el-button>
                </el-col>
                <el-col :span="2">
                    <el-button type="primary" icon="el-icon-minus" size="mini" @click="lastWeek">上一周</el-button>
                </el-col>
                <el-col :span="2">
                    <el-button type="success" icon="el-icon-s-operation" size="mini" @click="getList"> 本周 </el-button>
                </el-col>
                <el-col :span="2">
                    <el-button type="primary" icon="el-icon-plus" size="mini" @click="nextWeek"> 下一周 </el-button>
                </el-col>

            </el-row>
        </el-card>
        <el-card style="margin: 5px ">
            <el-row :gutter="20">
                <el-col :span="24">
                    <div align="center">{{  monday + '(周一)'  }}-{{  sunday + '(周日)'  }}</div>
                </el-col>
            </el-row>
        </el-card>
        <el-table :data="dataList" :span-method="objectSpanMethod" border style="width: 100%; margin-top: 20px">
            <el-table-column prop="name" label="医生">
            </el-table-column>
            <el-table-column prop="dept" label="科室">
            </el-table-column>
            <el-table-column prop="scheduleTime" label="时间/日期">
                <template slot-scope="scope">
                    <dict-tag :options="dict.type.his_schedule_time" :value="scope.row.scheduleTime" />
                </template>
            </el-table-column>
            <el-table-column prop="week1.type" :label="monday + '(周一)'">
            </el-table-column>
            <el-table-column prop="week2.type" :label="tuesday + '(周二)'">
            </el-table-column>
            <el-table-column prop="week3.type" :label="wednesday + '(周三)'">
            </el-table-column>
            <el-table-column prop="week4.type" :label="thursday + '(周四)'">
            </el-table-column>
            <el-table-column prop="week5.type" :label="friday + '(周五)'">
            </el-table-column>
            <el-table-column prop="week6.type" :label="saturday + '(周六)'">
            </el-table-column>
            <el-table-column prop="week7.type" :label="sunday + '(周日)'">
            </el-table-column>
            <el-table-column label="操作">
                <template slot-scope="scope">
                    <el-button size="mini" @click="handleEdit(scope.$index, scope.row)">编辑</el-button>
                </template>
            </el-table-column>
        </el-table>
        <el-table>
            
        </el-table>
    </div>
</template>
  <script>
  import { listSchedule, updateSchedule } from "@/api/his/schedule";
  
  export default {
      components: {},
      props: [],
      dicts: ['his_schedule_time'],
      data() {
          return {
              monday: "",
              tuesday: "",
              wednesday: "",
              thursday: "",
              friday: "",
              saturday: "",
              sunday: "",
              base: "",
              nextMonday: "",
              lastMonday: "",
              hisSchedule: {
                  monday: "",
                  deptName: "",
                  doctName: ""
              },
              dataList: [
                  { name: "" },
                  { dept: "" },
                  { monday: "" },
                  { scheduleTime: "" },
                  { week1: { date: {}, type: {} } },
                  { week2: { date: {}, type: {} } },
                  { week3: { date: {}, type: {} } },
                  { week4: { date: {}, type: {} } },
                  { week5: { date: {}, type: {} } },
                  { week6: { date: {}, type: {} } },
                  { week7: { date: {}, type: {} } },
              ],
              deptList: [{
                  id: "",
                  name: "",
                  children: [{
                      doctor: ""
                  }]
              }],
              docts: [],
              depts: [],
          }
      },
      computed: {},
      watch: {},
      created() { this.getList(); },
      mounted() { },
      methods: {
          submitForm() {//搜索
              this.hisSchedule.monday = this.monday;
              this.getList();
          },
          resetForm() {//重置
              this.getList();
          },
          getList() {//初始化当周排班表
              this.hisSchedule.monday = null;
              listSchedule(this.hisSchedule).then(r => {
                  if (r.code == 200 && r.data.length != 0) {
                      this.dataList = r.data;
                      //得出周一到周日的日期
                      this.monday = this.dataList[0].monday;
                      this.nextMonday = this.getNextDate(this.monday, 7);//下周一
                      this.lastMonday = this.getNextDate(this.monday, -7);//上周一
                      this.tuesday = this.getNextDate(this.monday, 1);
                      this.wednesday = this.getNextDate(this.monday, 2);
                      this.thursday = this.getNextDate(this.monday, 3);
                      this.friday = this.getNextDate(this.monday, 4);
                      this.saturday = this.getNextDate(this.monday, 5);
                      this.sunday = this.getNextDate(this.monday, 6);
                      console.log(this.dataList);
                      console.log("本周一", this.monday);
                  } else {
                      this.$message({
                          message: '暂无本周排班',
                          type: 'warning'
                      });
                  }
              });
          },
          /**
           * 获取指定日期的前几天，后几天
           * 
           * @param {*} date代表指定日期，格式：2018-09-27 
           * @param {*} day 代表天数，-1代表前一天，1代表后一天
           */
          getNextDate(date, day) {
              var dd = new Date(date);
              dd.setDate(dd.getDate() + day);
              var y = dd.getFullYear();
              var m = dd.getMonth() + 1 < 10 ? "0" + (dd.getMonth() + 1) : dd.getMonth() + 1;
              var d = dd.getDate() < 10 ? "0" + dd.getDate() : dd.getDate();
              return y + "-" + m + "-" + d;
          },
          objectSpanMethod({ row, column, rowIndex, columnIndex }) {//将第一列和第二轮的单元格每三行合并
              if (columnIndex === 1 || columnIndex === 0) {
                  if (rowIndex % 3 === 0) {
                      return {
                          rowspan: 3,
                          colspan: 1
                      };
                  } else {
                      return {
                          rowspan: 0,
                          colspan: 0
                      };
                  }
              }
          },
          handleEdit(index, row) {
              console.log(index, row);
          },
          lastWeek() {//上一周
              console.log("上周一", this.lastMonday);
              this.hisSchedule.monday = this.lastMonday;
              console.log(this.hisSchedule);
              listSchedule(this.hisSchedule).then(r => {
                  console.log("respose", r);
                  if (r.code == 200 && r.data.length != 0) {
                      this.dataList = r.data;
                      console.log(this.dataList);
                      //得出周一到周日的日期
                      let monday1 = this.dataList[0].monday;
                      this.monday = this.getNextDate(monday1, 0);
                      this.nextMonday = this.getNextDate(this.monday, 7);//下周一
                      this.lastMonday = this.getNextDate(this.monday, -7);//上周一
                      this.tuesday = this.getNextDate(this.monday, 1);
                      this.wednesday = this.getNextDate(this.monday, 2);
                      this.thursday = this.getNextDate(this.monday, 3);
                      this.friday = this.getNextDate(this.monday, 4);
                      this.saturday = this.getNextDate(this.monday, 5);
                      this.sunday = this.getNextDate(this.monday, 6);
                      console.log("上周一", this.lastMonday);
                  } else {
                      this.$message({
                          message: '无上周排班',
                          type: 'warning'
                      });
                  }
              });
          },
          nextWeek() {
              console.log("下周一", this.nextMonday);
              this.hisSchedule.monday = this.nextMonday;
              console.log(this.hisSchedule);
              listSchedule(this.hisSchedule).then(r => {
                  console.log("respose", r);
                  if (r.code == 200 && r.data.length != 0) {
                      this.dataList = r.data;
                      console.log(this.dataList);
                      //得出周一到周日的日期
                      let monday1 = this.dataList[0].monday;
                      this.monday = this.getNextDate(monday1, 0);
                      this.nextMonday = this.getNextDate(this.monday, 7);//下周一
                      this.lastMonday = this.getNextDate(this.monday, -7);//上周一
                      this.tuesday = this.getNextDate(this.monday, 1);
                      this.wednesday = this.getNextDate(this.monday, 2);
                      this.thursday = this.getNextDate(this.monday, 3);
                      this.friday = this.getNextDate(this.monday, 4);
                      this.saturday = this.getNextDate(this.monday, 5);
                      this.sunday = this.getNextDate(this.monday, 6);
                      console.log("上周一", this.lastMonday);
                  } else {
                      this.$message({
                          message: '无下周排班',
                          type: 'warning'
                      });
                  }
              });
          }
  
      }
  }
  
  </script>
 