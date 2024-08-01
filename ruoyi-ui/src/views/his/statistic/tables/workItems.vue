<template>
    <el-table :data="tableData" border style="width: 100%">
        <el-table-column prop="id" label="挂号ID" width="180">
        </el-table-column>
        <el-table-column prop="doctorNumber" label="医生编号" width="180">
        </el-table-column>
        <el-table-column prop="doctorName" label="医生姓名">
        </el-table-column>
        <el-table-column prop="registration" label="挂号费用">
        </el-table-column>
        <el-table-column prop="patientName" label="患者姓名">
        </el-table-column>
        <el-table-column prop="consultationTime" label="就诊时间">
        </el-table-column>
    </el-table>
</template>
  
  <script>
  export default {
      data() {
          return {
              tableData: [{
                  date: '2016-05-02',
                  name: '王小虎',
                  address: '上海市普陀区金沙江路 1518 弄'
              }, {
                  date: '2016-05-04',
                  name: '王小虎',
                  address: '上海市普陀区金沙江路 1517 弄'
              }, {
                  date: '2016-05-01',
                  name: '王小虎',
                  address: '上海市普陀区金沙江路 1519 弄'
              }, {
                  date: '2016-05-03',
                  name: '王小虎',
                  address: '上海市普陀区金沙江路 1516 弄'
              }]
          }
      }
  }
  </script>