echo "Start"
date --date='0 days ago' "+%Y-%m-%d %H:%M:%S"
rm -rf GPT-WEB-MANAGER
git clone -b GPT-WEB-MANAGER https://gitee.com/luanyutop/GPT-WEB-MANAGER.git
cd GPT-WEB-MANAGER
echo "停止容器"
docker stop chatgpt
docker rm chatgpt
echo "删除镜像"
docker rmi chatgpt:latest
echo "构建镜像"
docker build -t  chatgpt .
echo "启动镜像"
docker run -id --name=chatgpt -v /home/www/logs:/home/www/logs -v /home/www/uploadPath:/home/www/uploadPath -p  39004:80  chatgpt
echo "启动成功"
#http://124.222.218.80:35113/hook?access_key=YbD49O1Nrn6DXY5IdvWHBi1Ji9j8lwyLvJEapcNsqLoyzz5V&param=aaa