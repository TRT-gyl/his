<template>
  <div class="app-container">
    <el-tabs v-model="activeName" @tab-click="changeClick">
      <el-tab-pane label="药品总库存" name="first" >
        <TotalRepertory></TotalRepertory>
      </el-tab-pane>
      <el-tab-pane label="批次库存及价格" name="second" width="50%">
        <BetchRepertory></BetchRepertory>
      </el-tab-pane>
    </el-tabs>
  </div>
</template>

<script>
import TotalRepertory from './subList/totalRepertory'
import BetchRepertory from './subList/batchRepertory'

export default {
  name: "Repertory",
  components: { TotalRepertory,BetchRepertory },
  data() {
    return {
      activeName: 'first',
    };
  },
  created() {

  },
  methods: {
    //标签切换事件
    changeClick(tab,event){
      /* console.log(tab);
      console.log(tab.name);
      console.log(event); */
      //this.$router.push("/his/warehousing/list");
    }
  }
};
</script>
