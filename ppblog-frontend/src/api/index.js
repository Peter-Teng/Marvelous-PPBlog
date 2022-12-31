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

    getPublicArticles(pageNum, pageSize=5) {
        return axios.get(paths.publicArticlesUrl, {
            params: {
                "pageNum": pageNum,
                "pageSize": pageSize
            }
        });
    }
}