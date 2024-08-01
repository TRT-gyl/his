<template>
  <div>
    <el-card class="box-card" style="margin: 10px">
      <el-row>
        挂号单ID &nbsp;<el-input
          placeholder="请输入挂号单"
          v-model="idcard"
          style="width: 30%"
        ></el-input>
        患者姓名&nbsp;<el-input
          placeholder="请输入患者姓名"
          v-model="idcard"
          style="width: 30%"
        ></el-input>
        <el-button type="primary" icon="el-icon-search" v-on:click="dianji"
          >查询</el-button
        >
      </el-row>
    </el-card>

    <div v-show="xianshi">
      <el-table :data="tableData" border style="width: 100%">
        <el-table-column prop="date" label="订单号" width="180">
        </el-table-column>
        <el-table-column prop="name" label="挂号单号" width="180">
        </el-table-column>
        <el-table-column prop="address" label="患者姓名"> </el-table-column>
        <el-table-column prop="address" label="总金额"> </el-table-column>
        <el-table-column prop="address" label="支付类型"> </el-table-column>
        <el-table-column prop="address" label="订单状态"> </el-table-column>
        <el-table-column prop="address" label="创建时间"> </el-table-column>
        <el-table-column prop="address" label="操作"> <el-button type="primary" plain>查看详情</el-button></el-table-column>
      </el-table>
      <div class="block">
    <span class="demonstration">分页查询，未完善</span>
    <el-pagination
      @size-change="handleSizeChange"
      @current-change="handleCurrentChange"
      :current-page="currentPage4"
      :page-sizes="[100, 200, 300, 400]"
      :page-size="100"
      layout="total, sizes, prev, pager, next, jumper"
      :total="400">
    </el-pagination>
  </div>
    </div>
  </div>
</template>

<script>
//支付组件
import QRCode from "qrcodejs2";
export default {
  data() {
    return {

      xianshi: false,
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
    };
  },
  methods: {
    handleChange(value) {},
    dianji() {
      this.xianshi = true;
    },


  },
};
</script>




