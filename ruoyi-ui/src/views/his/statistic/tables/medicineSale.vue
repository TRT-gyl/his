<template>
    <el-table :data="tableData" border style="width: 100%">
        <el-table-column prop="id" label="药品ID" width="70">
        </el-table-column>
        <el-table-column prop="name" label="药品名称" >
        </el-table-column>
        <el-table-column prop="price" label="药品价格" >
        </el-table-column>
        <el-table-column prop="saleNumbers" label="交易数量" >
        </el-table-column>
        <el-table-column prop="saleTotalPrice" label="交易总价" >
        </el-table-column>
        <el-table-column prop="saleTime" label="交易时间" >
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