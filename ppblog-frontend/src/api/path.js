const baseUrl="http://202.38.247.210:8888"

const paths = {
    loginUrl: baseUrl + "/general/user/login",
    registerUrl: baseUrl + "/general/user/register",
    infoUrl: baseUrl + "/general/user/info",
    publicArticlesUrl: baseUrl + "/general/article/list",
    getNewsUrl: baseUrl + "/general/news/list",
    getTagsUrl: baseUrl + "/general/tag/list",
    getArticleByTagUrl: baseUrl + "/general/article/tagList",
    getArticleDetailUrl: baseUrl + "/general/article",
    getLinksUrl: baseUrl + "/general/link/list",
    getStatisticUrl: baseUrl + "/general/statistic/overview",
    getTagStatisticUrl: baseUrl + "/general/statistic/tag",
    getLinkStatisticUrl: baseUrl + "/general/statistic/link",
    getBlogRecordUrl: baseUrl + "/general/statistic/record",
    
    hitokotoURL: "https://v1.hitokoto.cn/"
}

export default paths