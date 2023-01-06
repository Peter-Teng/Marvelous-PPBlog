const drawCharts = {
    blogChart: (echarts, statistics, loading) => {
        //需要获取到element,所以是onMounted的Hook
        let blogChart = echarts.init(document.getElementById("blogChart"));
        let blogData = {
            date: [],
            articles: [],
            links: [],
            news: [],
            tags: []
        }
        for (var i = statistics.length - 1; i >= 0; i--) {
            for (let key in statistics[i]) {
                if (key in blogData) {
                    blogData[key].push(statistics[i][key])
                }
            }
        }
        for (var i = 0; i < blogData.date.length; i++) {
            var strArr = blogData.date[i].split('-')
            blogData.date[i] = strArr[0] + "-" + strArr[1]
        }
        console.log(blogData)
        // 绘制图表
        blogChart.setOption({
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
                    data: blogData.date
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
                    data: blogData.articles
                },
                {
                    name: '标签',
                    type: 'line',
                    stack: 'Total',
                    areaStyle: {},
                    emphasis: {
                        focus: 'series'
                    },
                    data: blogData.articles
                },
                {
                    name: '导航',
                    type: 'line',
                    stack: 'Total',
                    areaStyle: {},
                    emphasis: {
                        focus: 'series'
                    },
                    data: blogData.links
                },
                {
                    name: '公告',
                    type: 'line',
                    stack: 'Total',
                    areaStyle: {},
                    emphasis: {
                        focus: 'series'
                    },
                    data: blogData.news
                },
            ]
        });
        window.onresize = function () {//自适应大小
            overviewChart.resize();
        };
        loading.value = false
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