修改Account.java中的namespace, ak, sk 为目标桶的信息
修改postUploadFile.jsp/postUploadText.jsp中的目标tenant [tenant1.hcp-demo.hcpdemo.com]
可以修改PostDemo.java中的上传目标key（25行）

配置HCP目标桶的CORS
<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<CORSConfiguration>
    <CORSRule>
        <AllowedOrigin>*</AllowedOrigin>
        <AllowedMethod>GET</AllowedMethod>
        <AllowedMethod>PUT</AllowedMethod>
        <AllowedMethod>POST</AllowedMethod>
        <AllowedHeader>*</AllowedHeader>
        <MaxAgeSeconds>3000</MaxAgeSeconds>
    </CORSRule>
</CORSConfiguration>

启动工程