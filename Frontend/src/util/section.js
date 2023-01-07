   const createBtn = document.getElementById('add-order-section');
   const getAllBtn = document.getElementById("get-all-section");
   const getOneBtn = document.getElementById("get-one-section");

   const createSection = document.getElementById("create-order");
   const getAllSection = document.getElementById("get-all");
   const getOneSection = document.getElementById("choose-order");

   createBtn.addEventListener("click", () => {
       createSection.classList.add("active");
       getOneSection.classList.remove("active");
       getAllSection.classList.remove("active");
   });

      getAllBtn.addEventListener("click", () => {
          createSection.classList.remove("active");
          getOneSection.classList.remove("active");
          getAllSection.classList.add("active");
      });

    getOneBtn.addEventListener("click", () => {
        createSection.classList.remove("active");
        getOneSection.classList.add("active");
        getAllSection.classList.remove("active");
    });