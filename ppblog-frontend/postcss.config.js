module.exports = () => ({
    plugins: [
        require('autoprefixer')(),
        require('postcss-pxtorem')({
            rootValue: 150,
            propList: ['*']
        })
    ]
})