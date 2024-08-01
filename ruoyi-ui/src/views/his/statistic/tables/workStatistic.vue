<template>
    <el-table :data="tableData" border style="width: 100%">
        <el-table-column prop="doctorNumber" label="医生编号" width="180">
        </el-table-column>
        <el-table-column prop="doctorName" label="医生姓名">
        </el-table-column>
        <el-table-column prop="registrationTotalPrice" label="挂号总额">
        </el-table-column>
        <el-table-column prop="registrationNumber" label="就诊数量">
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