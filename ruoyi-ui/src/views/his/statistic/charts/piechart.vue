<template>
    <div :id="chartId" style="width: 600px;height:400px;"></div>
</template>

<script>
export default {
    props: ['chartId', 'option'],
    name: 'pieCharts',
    methods: {
        myEcharts() {
            // 基于准备好的dom，初始化echarts实例
            let myChart = this.$echarts.init(document.getElementById(this.chartId));
            myChart.setOption(this.option);
        }
    },
    mounted() {
        this.myEcharts();
    }
}
</script>