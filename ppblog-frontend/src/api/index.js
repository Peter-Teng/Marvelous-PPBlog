import axios from "../utils/request"
import paths from "./path"

const jinrishici = require('jinrishici');

export default {
    login(user) {
        return axios.post(paths.loginUrl, user)
    },

    register(user) {
        return axios.post(paths.registerUrl, user)
    },

    getBackgrounds() {
        return axios.get(paths.backgroundUrl)
    },

    getInfo() {
        return axios.get(paths.infoUrl)

    },

    getPoem(content) {
        jinrishici.load(result => {
            content.value = result.data.content
        });
    },

    getPublicArticles(pageNum, pageSize = 5) {
        return axios.get(paths.getPublicArticlesUrl, {
            params: {
                "pageNum": pageNum,
                "pageSize": pageSize
            }
        });
    },

    getNews() {
        return axios.get(paths.getNewsUrl)
    },

    getTags() {
        return axios.get(paths.getTagsUrl)
    },

    getHitokoto() {
        return axios.get(paths.hitokotoURL)
    },

    getPublicArticlesByTag(tagName, pageNum, pageSize = 10) {
        if (tagName === "all") {
            return this.getPublicArticles(pageNum, pageSize)
        } else {
            return axios.get(paths.getArticleByTagUrl, {
                params: {
                    "tagName": tagName,
                    "pageNum": pageNum,
                    "pageSize": pageSize
                }
            })
        }
    },

    getArticleById(articleId) {
        return axios.get(`${paths.getArticleDetailUrl}/${articleId}`)
    },

    getLinks() {
        return axios.get(paths.getLinksUrl)
    },

    getOverviewStatistic() {
        return axios.get(paths.getStatisticUrl);
    },

    getTagStatistic() {
        return axios.get(paths.getTagStatisticUrl);
    },

    getLinkStatistic() {
        return axios.get(paths.getLinkStatisticUrl);
    },

    getBlogRecords() {
        return axios.get(paths.getBlogRecordUrl);
    },

    uploadImage(image, squeeze) {
        const data = new FormData()
        data.append("image", image.raw)
        data.append("squeeze", squeeze)
        const token = window.sessionStorage.getItem("token")
        console.log(squeeze)
        return axios.post(paths.imageUploadUrl, data, { headers: { "token": token, "Content-Type": "multipart/form-data;" } })
    },

    postArticle(article) {
        const token = window.sessionStorage.getItem("token")
        return axios.post(paths.postArticleUrl, article, { headers: { "token": token } })
    },
}