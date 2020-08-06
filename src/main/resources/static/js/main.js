var messageApi = Vue.resource('/findall{/id}')
Vue.component('valute-row', {
    props:['valute'],
    template: '<div><>{{valute.name}}</div>'
});
Vue.component('valutes-list',{
    props: ['messages'],
    template: '<div><valute-row v-for="valute in valutes" :valute="valute"/></div>'
    created: function () {
        messageApi.get().then(result=>
        result.json().then(data=>
        data.forEach(valute=>this.valutes.push(valute))))
    }
});

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
                this.curss=resoult.body;
                console.log(resoult)
            }
            )
        },
        input2: function () {
            this.selected2='Рубль'
        }
    }
});