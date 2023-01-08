import BaseClass from "../util/baseClass";
import DataStore from "../util/DataStore";
import Section from "../util/section";
import OrderClient from "../api/orderClient";

/**
 * Logic needed for the view playlist page of the website.
 */
class AdminPage extends BaseClass {

    constructor() {
        super();
        this.bindClassMethods(['onGetOne', 'onGetAll', 'onCreate', 'onDelete', 'renderOrders', 'renderSingleOrder'], this);
        this.dataStore = new DataStore();
        //are states needed
    }

    /**
     * Once the page has loaded, set up the event handlers and fetch the concert list.
     */
    async mount() {
        document.getElementById('get-by-id-form').addEventListener('submit', this.onGetOne);
        document.getElementById('get-all-form').addEventListener('submit', this.onGetAll);
        document.getElementById('create-form').addEventListener('submit', this.onCreate);
        document.getElementById('delete-by-id-form').addEventListener('submit', this.onDelete);
        //do we need to create state?
        //this.datastore.addChangeListener(this.onStateChange);
        this.client = new OrderClient();

        const orders = await this.client.getAllOrders();
        if(orders && orders.length > 0){
        this.dataStore.set('orders', orders);
        }
    }
    // Render Methods --------------------------------------------------------------------------------------------------
    async renderSingleOrder() {
            let resultArea = document.getElementById("result-info");
            //not sure how datastore actaully works, using singular "order" may cause problems
            const order = this.dataStore.get("order");

            if (order) {
                resultArea.innerHTML = `
                    <div class="results">
                      <h4>${order.id}</h4>
                      <p>Date: ${order.order_date}</p>
                      <p>Status: ${order.status}</p>
                      <p>ProductId: ${order.product_id}</p>
                      <p>Customer Name: ${order.customer_name}</p>
                      <p>Customer Address: ${order.address}</p>
                    </div>
                `
            } else {
                resultArea.innerHTML = "No Item";
            }
        }

    async renderOrders() {
        let resultArea = document.getElementById("result-info");

        const orders = this.dataStore.get("orders");
        if(orders){
           resultArea.innerHTML = "";
           for(const order of orders){
           resultArea.innerHTML += `
              <div class="results">
                  <h4>${order.id}</h4>
                  <p>Date: ${order.order_date}</p>
                  <p>Status: ${order.status}</p>
                  <p>ProductId: ${order.product_id}</p>
                  <p>Customer Name: ${order.customer_name}</p>
                  <p>Address: ${order.address}</p>
              </div>
              `
           }
        }
        else {
            resultArea.innerHTML = "No Orders";
        }
    }

    // Event Handlers --------------------------------------------------------------------------------------------------

    async onGetOne(event) {
        // Prevent the page from refreshing on form submit
        event.preventDefault();

        let id = document.getElementById("id-field").value;
        this.dataStore.set("order", null);

        let result = await this.client.getOrder(id, this.errorHandler);
        this.dataStore.set("order", result);
        if (result) {
            this.showMessage(`Got ${result.id}!`)
        } else {
            this.errorHandler("Error doing GET!  Try again...");
        }
        this.renderSingleOrder();
    }

    async onGetAll(event) {
            // Prevent the page from refreshing on form submit
            event.preventDefault();

            this.dataStore.set("orders", null);

            let result = await this.client.getAllOrders(this.errorHandler);
            this.dataStore.set("orders", result);
            if (result) {
                this.showMessage(`Got ${result}!`)
            } else {
                this.errorHandler("Error doing GET!  Try again...");
            }
            this.renderOrders();
        }

    async onCreate(event) {
        // Prevent the page from refreshing on form submit
        event.preventDefault();
        this.dataStore.set("order", null);

        let product_id = document.getElementById("create-product-field").value;
        let name = document.getElementById("create-name-field").value;
        let address = document.getElementById("create-address-field").value;

        const createdOrder = await this.client.createOrder(product_id, name, address, this.errorHandler);
        this.dataStore.set("order", createdOrder);

        if (createdOrder) {
            this.showMessage(`Created ${createdOrder.product_id}!`)
        } else {
            this.errorHandler("Error creating!  Try again...");
        }
    }

    async onDelete(event) {
        // Prevent the page from refreshing on form submit
        event.preventDefault();

        let id = document.getElementById("del-id-field").value;
        this.dataStore.set("order", null);

        let result = await this.client.deleteOrder(id, this.errorHandler);
        this.dataStore.set("order", result);
        if (result) {
            this.showMessage(`Deleted ${result}!`);
            document.getElementById("result-info").innerHTML = id + " Deleted!";
        } else {
            this.errorHandler("Error doing DELETE!  Try again...");
        }
    }
}

/**
 * Main method to run when the page contents have loaded.
 */
const main = async () => {
    const adminPage = new AdminPage();
    adminPage.mount();
};

window.addEventListener('DOMContentLoaded', main);
