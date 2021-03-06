$(function(){
var dataMap = {};
var myChart = echarts.init(document.getElementById('client5'));
option = {
	    title : {
	        text: '',
	        subtext: '情景模拟数据',
	        x:'center'
	    },
	    tooltip : {
	        trigger: 'item',
	        formatter: "{a} <br/>{b} : {c} ({d}%)"
	    },
	    legend: {
	        x : 'center',
	        y : 'bottom'
	    },
	    toolbox: {
	        show : true,
	        feature : {
	            mark : {show: true},
	            dataView : {show: true, readOnly: false},
	            magicType : {
	                show: true,
	                type: ['pie', 'funnel']
	            },
	            restore : {show: true},
	            saveAsImage : {show: true}
	        }
	    },
	    calculable : true,
	    series : [
	        {
	            name:'车用途种类',
	            type:'pie',
	            radius : [20, 110],
	            center : ['25%', '50%'],
	            roseType : 'radius',
	            label: {
	                normal: {
	                    show: false
	                },
	                emphasis: {
	                    show: true
	                }
	            },
	            lableLine: {
	                normal: {
	                    show: false
	                },
	                emphasis: {
	                    show: true
	                }
	            },
	            data:[
	                {value:10, name:'长租车'},
	                {value:15, name:'政府用车'},
	                {value:45, name:'短租车'},
	                {value:35, name:'公司用车'},
	                {value:25, name:'破损车'},
	                {value:100, name:'一般用车'}
	            ]
	        },
	        {
	            name:'占比',
	            type:'pie',
	            radius : [30, 110],
	            center : ['75%', '50%'],
	            roseType : 'area',
	            data:[
	                {value:10, name:'白领'},
	                {value:5, name:'学生'},
	                {value:15, name:'教师'},
	                {value:25, name:'家庭'},
	                {value:20, name:'金领'}
	            ]
	        }
	    ]
	};

myChart.setOption(option);
})