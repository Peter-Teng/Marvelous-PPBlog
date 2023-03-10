import MarkdownIt from 'markdown-it';
import hljs from 'highlight.js'
import 'highlight.js/styles/atom-one-dark.css'

const md = new MarkdownIt({
    html: true,
    linkify: true,
    typographer: true,
    highlight: function (str, lang) {
        // 此处判断是否有添加代码语言
        if (lang && hljs.getLanguage(lang)) {
            try {
                // 得到经过highlight.js之后的html代码
                const preCode = hljs.highlight(lang, str, true).value
                // 以换行进行分割
                const lines = preCode.split(/\n/).slice(0, -1)
                let html = ''
                // 添加代码语言
                if (lines.length) {
                    html += '<b class="name" style="color: #393E46; background-color: #F6F6F6; text-shadow: none; box-shadow: 1px 1px 3px 3px white; border-radius: 5px; float: right;">' 
                    + lang + '</b>'
                }
                // 添加自定义行号
                html += lines.map((item, index) => {
                    return '<li><span class="line-num" data-line="' + (index + 1) + '"></span>' + item + '</li>'
                }).join('')
                html = '<ol>' + html + '</ol>'
                return '<pre class="hljs"><code>' +
                    html +
                    '</code></pre>'
            } catch (__) { }
        }
        // 未添加代码语言，此处与上面同理
        const preCode = md.utils.escapeHtml(str)
        const lines = preCode.split(/\n/).slice(0, -1)
        let html = lines.map((item, index) => {
            return '<li><span class="line-num" data-line="' + (index + 1) + '"></span>' + item + '</li>'
        }).join('')
        html = '<ol>' + html + '</ol>'
        return '<pre class="hljs"><code>' +
            html +
            '</code></pre>'
    }
})

export default md