import React from "react";


function HomeSectionCard() {
  return (
    <div className="cursor-pointer flex flex-col items-center bg-white rounded-lg shadow-lg overflow-hidden w-[15rem] mx-3 border ">
      <div className="h-[13rem] w-[10rem]">
        <img
          className="object-cover object-top w-full h-full "
          src="https://cotton4u.vn/files/product/thumab/400/2024/07/31/bf5ddde9bd5745bd01e69c3280568c49.webp"
          alt=""
        />
      </div>
      <div className="p-4">
        <h3 className="text-lg font-medium text-gray-900">Đầm hoa xòe tay lỡ</h3>
        <p className="mt-2 text-sm text-gray-500">
          Đầm xòe họa tiết hoa mang đến vẻ ngoài nhẹ nhàng
        </p>
      </div>
    </div>
  );
}

export default HomeSectionCard;
