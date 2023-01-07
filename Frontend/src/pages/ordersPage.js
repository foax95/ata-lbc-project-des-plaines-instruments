import BaseClass from "../util/baseClass";
import DataStore from "../util/DataStore";
import OrdersClient from "../api/ordersClient";

/**
 * Logic needed for the view playlist page of the website.
 */
class OrdersPage extends BaseClass {

    constructor() {
        super();
        this.bindClassMethods(['onDelete', 'onGet', 'onCreate', 'renderOrders'], this);
        this.dataStore = new DataStore();
    }

    /**
     * Once the page has loaded, set up the event handlers and fetch the concert list.
     */
    async mount() {
        document.getElementById('get-by-id-form').addEventListener('submit', this.onGet);
        document.getElementById('create-form').addEventListener('submit', this.onCreate);
        document.getElementById('delete-by-id-form').addEventListener('submit', this.onDelete);
        this.client = new OrdersClient();

        this.renderOrders();
    }

    // Render Methods --------------------------------------------------------------------------------------------------

     async renderOrder(order) {
        let resultArea = document.getElementById("result-info");

        resultArea.innerHTML = `
            <div>ID: ${order.id}</div>
            <div>Name: ${order.product_id}</div>
            <div>Name: ${order.order_date}</div>
            <div>Name: ${order.status}</div>
            <div>Name: ${order.customer_name}</div>
            <div>Name: ${order.address}</div>
        `;
    }

    async renderOrders() {
        let resultArea = document.getElementById("result-info");
        const orders = await this.client.getOrders(this.errorHandler);

        if (orders) {
            resultArea.innerHTML = "";
            for (const order of orders) {
                 resultArea.innerHTML += `
                    <div>ID: ${order.id}</div>
                    <div>Name: ${order.product_id}</div>
                    <div>Name: ${order.order_date}</div>
                    <div>Name: ${order.status}</div>
                    <div>Name: ${order.customer_name}</div>
                    <div>Name: ${order.address}</div>
                `;
            }
        } else {
            resultArea.innerHTML = "No Orders";
        }
    }

    // Event Handlers --------------------------------------------------------------------------------------------------

    async onGet(event) {
        // Prevent the page from refreshing on form submit
        event.preventDefault();

        let id = document.getElementById("get-id-field").value;
        this.dataStore.set("orders", null);

        let result = await this.client.getOrder(id, this.errorHandler);
        this.dataStore.set("orders", result);
        if (result) {
            this.showMessage(`Got ${result.id}!`)
            this.renderOrder(result);
        } else {
            this.errorHandler("Error doing onGet!  Try again...");
        }
    }

    async onDelete(event) {
        // Prevent the page from refreshing on form submit
        event.preventDefault();

        let id = document.getElementById("delete-id-field").value;
        this.dataStore.set("orders", null);

        let result = await this.client.deleteOrder(id, this.errorHandler);
        this.dataStore.set("orders", result);
        console.log(result);
        if (result == 204) {
            this.showMessage("Deleted " + id +"!");
        } else {
            this.errorHandler("Error doing onDelete!  Try again...");
        }
        this.renderOrders();
    }

    async onCreate(event) {
        // Prevent the page from refreshing on form submit
        event.preventDefault();
        this.dataStore.set("orders", null);

        let pid = document.getElementById("create-pid-field").value;
        let name = document.getElementById("create-name-field").value;
        let address = document.getElementById("create-address-field").value;

        const createdOrder = await this.client.createOrder(pid, name, address, this.errorHandler);
        this.dataStore.set("orders", createdOrder);

        if (createdOrder) {
            this.showMessage(`Created ${createdOrder.id}!`)
        } else {
            this.errorHandler("Error creating!  Try again...");
        }
        this.renderOrders();
    }
}

/**
 * Main method to run when the page contents have loaded.
 */
const main = async () => {
    const ordersPage = new OrdersPage();
    ordersPage.mount();
};

window.addEventListener('DOMContentLoaded', main);
