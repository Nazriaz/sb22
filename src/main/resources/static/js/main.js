var API = Vue.resource('/valutes{/id}');
Vue.component('valutes-list', {
    props: ['valutes', 'input2'],
    data: function () {
        return {
            text: ''
        }
    },
    template: '<div>' +
        '<select v-model="text">' +
        '<option disabled value="">Выберите один из вариантов</option>-->' +
        '<option v-for="valute in valutes" :value="valute.id">{{valute.charCode}} ({{valute.name}})</option>' +
        '</select>' +
        '</div>',
    created: function () {
        API.get().then(result =>
            result.json().then(data =>
                data.forEach(valute => this.valutes.push(valute))))
    },
    watch: {
        text: function () {
            this.input2(this.text);
        }
    }
});
Vue.component('calc', {
    props: ['valute1', 'valute2'],
    data: function () {
        return {
            amount: 1,
            valute3: '',
            valute1FromServer: '',
            valute2FromServer: ''
        }
    },
    template: '<div>' +
        '<div><button @click="calc">KNOPKA</button></div>' +
        '<div><input v-model="amount"></div>' +
        '<div>RESULT = {{valute3}}</div>' +
        '</div>',
    methods: {
        calc: function () {
            const promis1 = API.get({id: this.valute1}).then(resoult => resoult.json().then(res => this.valute1FromServer = res));
            const promis2 = API.get({id: this.valute2}).then(resoult => resoult.json().then(res => this.valute2FromServer = res));
            Promise.all([promis1, promis2]).then(values => {
                var curs1 = this.valute1FromServer.value;
                var curs2 = this.valute2FromServer.value;
                var nom1 = this.valute1FromServer.nominal;
                var nom2 = this.valute2FromServer.nominal;
                this.valute3 = ((curs1 * nom2) * this.amount) / (nom1 * curs2);
                console.log(values)
            })
        }
    }
});

var app = new Vue({
    el: '#app',
    data: {
        valutes: [],
        selected1: 'selected1',
        selected2: 'selected2',
        resoult: 'JOPA'
    },
    methods: {
        input: function () {
            API.get().then(resoult => {
                    this.curss = resoult.body;
                    console.log(resoult)
                }
            )
        },
        input1: function (resoult) {
            this.selected1 = resoult
        },
        input2: function (resoult) {
            this.selected2 = resoult
        }
    }
});