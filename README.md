# ins_parent
### 微服务端口

服务名 | 端口 | 说明 
----|----|----
ins-eureka | 6000 | eureka
ins-gateway | 6001 | gateway网关
ins-mq-consume | 6010 | mq消费
ins-push-service | 6011 | 推送服务(包含短信，邮件)
ins-image-service | 6012 | 图片服务
ins-user-service | 6020 | 用户管理
ins-moment-service | 6021 | 动态管理
ins-collect-service | 6022 | 收藏功能
ins-base-service | 6023 | 基础功能(点赞，关注，后期把收藏也移过来)



### 前端端口

服务名 | 端口 | 说明 
----|----|----
index-web | 6100 | 商城主页
manager-web | 6110 | 后台管理页面



comment写类型 评论的是内容啊 还是回复

Comment:
{
id, //评论唯一id
owner_user_id, //发表评论的用户id
target_user_id, //评论的目标用户id
content, //评论内容
likeCount, //该评论被点赞的数量
created_at, //创建时间
parent_id, //评论的目标id
parent_type //评论的目标类型
}


照片墙
关注(用户)
点赞(动态)
收藏(动态)

转发(动态)
评论(动态)

