const drawCharts = {
    overviewChart: (echarts, statistics, loading) => {
        //需要获取到element,所以是onMounted的Hook
        let overviewChart = echarts.init(document.getElementById("blogChart"));
        // 绘制图表
        overviewChart.setOption({
            title: {
                text: '博客统计总览'
            },
            tooltip: {
                trigger: 'axis',
                axisPointer: {
                    type: 'cross',
                    label: {
                        backgroundColor: '#171717'
                    }
                }
            },
            legend: {
                data: ['文章', '标签', '导航', '公告']
            },
            grid: {
                left: '3%',
                right: '4%',
                bottom: '3%',
                containLabel: true
            },
            xAxis: [
                {
                    type: 'category',
                    boundaryGap: false,
                    data: ['2022-09', '2022-10', '2022-11', '2022-12', '2023-1']
                }
            ],
            yAxis: [
                {
                    type: 'value'
                }
            ],
            series: [
                {
                    name: '文章',
                    type: 'line',
                    stack: 'Total',
                    areaStyle: {},
                    emphasis: {
                        focus: 'series'
                    },
                    data: [0, 5, 7, 9, 11]
                },
                {
                    name: '标签',
                    type: 'line',
                    stack: 'Total',
                    areaStyle: {},
                    emphasis: {
                        focus: 'series'
                    },
                    data: [0, 2, 3, 4, 7]
                },
                {
                    name: '导航',
                    type: 'line',
                    stack: 'Total',
                    areaStyle: {},
                    emphasis: {
                        focus: 'series'
                    },
                    data: [12, 15, 19, 20, 21]
                },
                {
                    name: '公告',
                    type: 'line',
                    stack: 'Total',
                    areaStyle: {},
                    emphasis: {
                        focus: 'series'
                    },
                    data: [2, 2, 3, 4, 5]
                },
            ]
        });
        window.onresize = function () {//自适应大小
            overviewChart.resize();
        };
    },
    tagChart: (echarts, statistics, loading) => {
        //需要获取到element,所以是onMounted的Hook
        let tagChart = echarts.init(document.getElementById("tagChart"));
        let tagData = []
        for (let item of statistics) {
            tagData.push({
                value: Number(item.value),
                name: item.key,
            })
        }
        // 绘制图表
        tagChart.setOption({
            title: {
                text: '标签分类统计图'
            },
            tooltip: {
                trigger: 'item'
            },
            legend: {
                top: 'bottom'
            },
            series: [
                {
                    name: '标签分类',
                    type: 'pie',
                    radius: ['40%', '70%'],
                    avoidLabelOverlap: false,
                    itemStyle: {
                        borderRadius: 10,
                        borderColor: '#fff',
                        borderWidth: 2
                    },
                    label: {
                        show: false,
                        position: 'center'
                    },
                    emphasis: {
                        label: {
                            show: true,
                            fontSize: 25,
                            fontWeight: 'bold'
                        }
                    },
                    labelLine: {
                        show: false
                    },
                    data: tagData,
                }
            ]
        })
        window.onresize = function () {//自适应大小
            tagChart.resize();
        }
        loading.value = false
    },
    linkChart: (echarts, statistics, loading) => {
        //需要获取到element,所以是onMounted的Hook
        let linkChart = echarts.init(document.getElementById("linkChart"));
        let linkData = []
        for (let item of statistics) {
            linkData.push({
                value: Number(item.value),
                name: item.key,
            })
        }
        // 绘制图表
        linkChart.setOption({
            title: {
                text: '导航分类统计图'
            },
            tooltip: {
                trigger: 'item'
            },
            legend: {
                top: 'bottom'
            },
            series: [
                {
                    name: '导航分类',
                    type: 'pie',
                    radius: [25, 200],
                    center: ['50%', '50%'],
                    roseType: 'area',
                    itemStyle: {
                        borderRadius: 4
                    },
                    data: linkData,
                }
            ]
        })
        window.onresize = function () {//自适应大小
            tagChart.resize();
        }
        loading.value = false
    }
};

export default drawCharts;