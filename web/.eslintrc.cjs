module.exports = {
    "env": {
        "browser": true,
        "es2021": true
    },
    "extends": [
        "eslint:recommended",
        "plugin:vue/vue3-essential"
    ],
    "overrides": [
    ],
    "parserOptions": {
        "ecmaVersion": "latest",
        "sourceType": "module"
    },
    "plugins": [
        "vue",
        "jsdoc"
    ],
    "rules": {
        // 关闭名称校验
        'vue/multi-word-component-names': "off",
        "jsdoc/no-undefined-types":1,
    }
}
