const userStore = {
    data: {
        token: null
    },
    methods: {
        hasLogin: (userStore) => {
            if(userStore.data.token == null) {
                userStore.data.token = window.sessionStorage.getItem("token")
            }
            return userStore.data.token != null
        },
        login: (userStore, token) => {
            userStore.data.token = token
            window.sessionStorage.setItem("token", token)
        }, 
        logout: (userStore) => {
            userStore.data.token = null
            window.sessionStorage.removeItem("token")
        }
    }
}

export default userStore