// const baseUrl = "http://114.132.47.193:8888"
const baseUrl="http://202.38.247.210:8888"

const paths = {
    loginUrl: baseUrl + "/general/user/login",
    registerUrl: baseUrl + "/general/user/register",
    infoUrl: baseUrl + "/general/user/info",
    backgroundUrl: baseUrl + "/general/background/list",

    getPublicArticlesUrl: baseUrl + "/general/article/list",
    getNewsUrl: baseUrl + "/general/news/list",
    getTagsUrl: baseUrl + "/general/tag/list",
    getArticleByTagUrl: baseUrl + "/general/article/tagList",
    getArticleDetailUrl: baseUrl + "/general/article",
    getLinksUrl: baseUrl + "/general/link/list",
    getStatisticUrl: baseUrl + "/general/statistic/overview",
    getTagStatisticUrl: baseUrl + "/general/statistic/tag",
    getLinkStatisticUrl: baseUrl + "/general/statistic/link",
    getBlogRecordUrl: baseUrl + "/general/statistic/record",

    imageUploadUrl: baseUrl + "/admin/file/upload",

    getAllArticlesUrl: baseUrl + "/admin/article/list",
    postArticleUrl: baseUrl + "/admin/article/create",
    putArticleUrl: baseUrl + "/admin/article/modify",
    deleteArticleUrl: baseUrl + "/admin/article/",

    postTagUrl: baseUrl + "/admin/tag/create",
    putTagUrl: baseUrl + "/admin/tag/modify",
    deleteTagUrl: baseUrl + "/admin/tag/",

    postLinkUrl: baseUrl + "/admin/link/create",
    putLinkUrl: baseUrl + "/admin/link/modify",
    deleteLinkUrl: baseUrl + "/admin/link/",

    getAllNewsUrl: baseUrl + "/general/news/all",
    postNewsUrl: baseUrl + "/admin/news/create",
    putNewsUrl: baseUrl + "/admin/news/modify",
    deleteNewsUrl: baseUrl + "/admin/news/",

    putBackgroundUrl: baseUrl + "/admin/background/modify",

    hitokotoURL: "https://v1.hitokoto.cn/"
}

export default paths