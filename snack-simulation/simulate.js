class Snacks {

    #snacks
    constructor() {
        this.#snacks = [];
        this.#snacks.push({ "snackType": document.getElementById("gumQuan"), "quant": 10, "price": 0.50, "name": "Chewig-Gum" }, { "snackType": document.getElementById("chipsQuan"), "quant": 10, "price": 1, "name": "Chips" }, { "snackType": document.getElementById("chockQuan"), "quant": 10, "price": 2, "name": "Chockolate" });
    }

    decreaseQuan(element) {
        this.snacks[this.snacks.indexOf(element)]["quant"] -= 1;
        element.snackType.innerHTML = '<p ip=' + element.snackType.id + '>' + this.snacks[this.snacks.indexOf(element)]["quant"] + '</p>'
    }

    get snacks() {
        return this.#snacks;
    }
}

class Money {

    #moneyInTransaction
    #moneyInside
    #change
    constructor() {
        this.quart = document.getElementById("quart");
        this.half = document.getElementById("half");
        this.one = document.getElementById("one");
        this.five = document.getElementById("five");
        this.ten = document.getElementById("ten");
        this.#moneyInTransaction = document.getElementById("mit");
        this.#moneyInside = document.getElementById("mim");
        this.#change = document.getElementById("chng");
    }

    set moneyInTransaction(amount) {
        this.moneyInTransaction.innerHTML = '<p id="mit">' + amount + '</p>';
    }

    get moneyInTransaction() {
        return this.#moneyInTransaction;
    }

    set moneyInside(amount) {
        this.#moneyInside.innerHTML = '<p id="mim">' + (Number(this.#moneyInside.textContent) + amount) + '</p>'
    }

    set change(amount) {
        this.#change.innerHTML = '<p id="chng">' + amount + '</p>'
    }
}

class SnackMachine {
    #snackObj
    #moneyObj

    constructor() {
        this.chosenSnack = null;
        this.buy = document.getElementById("buy_button");
        this.cancel = document.getElementById("buy_cancel");
        this.gum = document.getElementById("gum").addEventListener("click", () => {
            document.getElementById("gum").style.border = "0.2rem solid black";
            document.getElementById("chock").style.border = null;
            document.getElementById("chips").style.border = null;
            this.addToCart("Chewig-Gum")
        })
        this.chock = document.getElementById("chock").addEventListener("click", () => {
            document.getElementById("gum").style.border = null;
            document.getElementById("chips").style.border = null;
            document.getElementById("chock").style.border = "0.2rem solid black";
            this.addToCart("Chockolate")
        })
        this.chips = document.getElementById("chips").addEventListener("click", () => {
            document.getElementById("gum").style.border = null;
            document.getElementById("chock").style.border = null;
            document.getElementById("chips").style.border = "0.2rem solid black";
            this.addToCart("Chips")
        })
        this.#snackObj = new Snacks();
        this.#moneyObj = new Money();
        this.#moneyObj.quart.addEventListener("click", () => {
            this.insertMoney(0.25);
        });
        this.#moneyObj.half.addEventListener("click", () => {
            this.insertMoney(0.50);
        });
        this.#moneyObj.one.addEventListener("click", () => {
            this.insertMoney(1.0);
        });
        this.#moneyObj.five.addEventListener("click", () => {
             this.insertMoney(5.0);
        });
        this.#moneyObj.ten.addEventListener("click", () => {
            this.insertMoney(10.0);
        });
    }

    addToCart(snackName) {
        console.log(snackName);
        this.chosenSnack = snackName;
    }

    insertMoney(amount) {
        this.#moneyObj.moneyInTransaction = '<p id="mit">' + (Number(this.#moneyObj.moneyInTransaction.textContent) + Number(amount)) + '</p>';
    }

    decreaseMoney(amount) {
        this.#moneyObj.change = '<p id="mit">' + (Number(this.#moneyObj.moneyInTransaction.textContent) - Number(amount)) + '</p>';
    }

    buyFun() {
        this.#snackObj.snacks.forEach(element => {
            if (element.name === this.chosenSnack) {
                if (element.quant > 0) {
                    if (Number(this.#moneyObj.moneyInTransaction.textContent) >= element.price) {
                        this.#snackObj.decreaseQuan(element);
                        this.#moneyObj.moneyInside = element.price;
                        this.decreaseMoney(element.price)
                        this.#moneyObj.moneyInTransaction = 0;
                    } else {
                        alert("Insufficient Funds!")
                    }
                } else {
                    alert("Not enough quantity!")
                }
            }
        });
    }
}

snack = new SnackMachine();

snack.buy.addEventListener("click", () => {
    snack.buyFun();
})

snack.cancel.addEventListener("click", () => {
    window.location.reload();
})
