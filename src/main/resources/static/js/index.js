export default new Vue({
    el: '#app',
    data() {
        return {
            labelList: []
        }
    },
    created() {
        console.log("aaaa")
        this.getLabelList()
    },
    methods: {
        getLabelList() {
            this.$http.get('/label/list').then(res => {
                console.log(res.data.data)
                this.labelList = res.data.data.list;
            })
        },
    }
});
