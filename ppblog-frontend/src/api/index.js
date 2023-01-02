import axios from "../utils/request"
import paths from "./path"
const jinrishici = require('jinrishici');

export default {
    login(user) {
        return axios.post(paths.loginUrl, user)
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
        return axios.get(paths.publicArticlesUrl, {
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
    }
}