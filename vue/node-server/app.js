//导入http内置模块
const http = require('http')
//解析URL地址
const urlModule=require('url')

//创建一个http服务器
const server = http.createServer();
//监听http服务器的request的请求
/* server.on('request',function(req,resp){
	const url = req.url;
	if (url == '/getscript') {
		var scriptStr = 'show()';
		resp.end(scriptStr);
	} else {
		resp.end(404);
	}
}); */
server.on('request',function(req,resp){
	const {pathname:url,query} = urlModule.parse(req.url,true);
	if (url == '/getscript') {
		var data = {
			name:"test",
			age:18,
			gender:'男孩'
		};
		var scriptStr = `${query.callback}(${JSON.stringify(data)})`;
		resp.end(scriptStr);
	} else {
		resp.end(404);
	}
});
//指定端口并启动服务器监听
server.listen(30000,function(){
	console.log('server listen at http://localhost:30000');
});