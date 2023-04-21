
class Section {

   constructor(){}

   async mount() {
       const createBtn = document.getElementById("add-order-section");
       const getAllBtn = document.getElementById("get-all-section");
       const getOneBtn = document.getElementById("get-one-section");
       const deleteBtn = document.getElementById("delete-section");

       const createSection = document.getElementById("create-order");
       const getAllSection = document.getElementById("get-all");
       const getOneSection = document.getElementById("choose-order");
       const deleteSection = document.getElementById("delete-order");

       createBtn.addEventListener("click", () => {
           createSection.classList.add("active");
           getOneSection.classList.remove("active");
           getAllSection.classList.remove("active");
          deleteSection.classList.remove("active");
       });

      getAllBtn.addEventListener("click", () => {
          createSection.classList.remove("active");
          getOneSection.classList.remove("active");
          getAllSection.classList.add("active");
          deleteSection.classList.remove("active");
      });

      getOneBtn.addEventListener("click", () => {
          createSection.classList.remove("active");
          getOneSection.classList.add("active");
          getAllSection.classList.remove("active");
          deleteSection.classList.remove("active");
      });

      deleteBtn.addEventListener("click", () => {
          createSection.classList.remove("active");
          getOneSection.classList.remove("active");
          getAllSection.classList.remove("active");
          deleteSection.classList.add("active");
      });
    }
}

const main2 = async () => {
    const section = new Section();
    section.mount();
};

window.addEventListener('DOMContentLoaded', main2);