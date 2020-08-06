// Vue.component('curs-list', {
//     template: '<div>List</div>'
// });
var messageApi = Vue.resource('/get{/id}')
var app = new Vue({
    el: '#app',
    // template: '<curs-list />',
    data: {
        curss: ['Рубль', 'Доллары', 'Тугрики'],
        selected: '',
        selected2: '',
        resoult:'sss'
    },
    methods:{
        input: function () {
            messageApi.get().then(resoult =>{
                this.resoult=resoult.body.name
                console.log(resoult)
            }
        )
        }
    }
});