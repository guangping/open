<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<link rel="stylesheet" type="text/css" href="${ctx}/static/css/data.css">
<div class="mainContent">
    <div class="mainList">
        <div class="dataMain">
            <div class="dataContent" id="dataAppForm">
                <h5>时间范围：</h5>
                <input type="text" placeholder="开始时间" onclick="laydate()" name="startTime" readonly="">
                <input type="text" placeholder="结束时间" onclick="laydate()" name="endTime" readonly="">
                <a id="dataAppSearch" class="btn">搜索</a>
            </div>
            <div class="dataNext">
                <div id="dataAppStats" style="width: 930px;height: 299px;"></div>
            </div>
        </div>
    </div>
</div>


<script type="text/javascript">
    var DataAppChart = {
        init: function (myChart) {
            var xData = [], yData = [];
            var param = ServiceUtils.getInputDomain('dataAppForm');
            param.pageIndex = 1;
            ServiceHttp.execute('${ctx}/admin/data/app?_=' + new Date().getTime(), param, function (cb) {
                myChart.hideLoading();
                if (cb.success) {
                    var data = cb.result.data || [];
                    $.each(data, function (index, value) {
                        xData.push(value.day_time || '');
                        yData.push(value.quantity || '');
                    });
                } else {
                    bootbox.alert(cb.message);
                }
            });
            var option = {
                title: {
                    text: '',
                },
                tooltip: {
                    show: true
                },
                legend: {
                    data: ['APP定制']
                },
                toolbox: {
                    show: true,
                    feature: {
                        dataView: {show: true, readOnly: false},
                        magicType: {show: true, type: ['line', 'bar']},
                        restore: {show: true},
                        saveAsImage: {show: true}
                    }
                },
                tooltip: {
                    trigger: 'axis'
                },
                calculable: true,
                xAxis: [
                    {
                        type: 'category',
                        data: xData
                    }
                ],
                yAxis: [
                    {
                        type: 'value'
                    }
                ],
                series: [
                    {
                        "name": "定制",
                        "type": "bar",
                        "data": yData
                    }
                ]
            };
            myChart.setOption(option,true);
        }
    }
</script>
<script type="text/javascript">
    // 路径配置
    require.config({
        paths: {
            echarts: '${ctx}/static/js/echarts'
        }
    });

    var myChart = null;
    // 使用
    require(
            [
                'echarts',
                'echarts/chart/line',
                'echarts/chart/bar'
            ],
            function (ec) {
                myChart = ec.init(document.getElementById('dataAppStats'));
                myChart.showLoading({
                    text: '加载中...'
                });
                $.ajaxSettings.async = false;

                DataAppChart.init(myChart)
            });
</script>
<script type="text/javascript">
    $(function () {
        $('#dataAppSearch').on('click', function (e) {
            DataAppChart.init(myChart);
        })
    })
</script>