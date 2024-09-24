import React from "react";
import AliceCarousel from "react-alice-carousel";
import "react-alice-carousel/lib/alice-carousel.css";
import { homeCaroselData } from "./MainCaroselData";

function MainCarosel() {
  const items = homeCaroselData.map((item) => <img src={item.image} />);
  return (
    <div className="z-1">
      <AliceCarousel
        mouseTracking
        items={items}
        controlsStrategy="alternate"
        disableButtonsControls
        disableDotsControls
        autoPlay
        autoPlayInterval={1500}
        infinite
      />
    </div>
  );
}

export default MainCarosel;
