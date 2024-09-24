import React, { useState, useRef } from "react";
import AliceCarousel from "react-alice-carousel";
import "react-alice-carousel/lib/alice-carousel.css";
import HomeSectionCard from "../HomeSectionCard/HomeSectionCard";
import ArrowBackIosNewIcon from "@mui/icons-material/ArrowBackIosNew";
import { Button } from "@mui/material";

function HomeSectionCarosel() {
  const responsive = {
    0: { items: 1 },
    720: { items: 3 },
    1024: { items: 5 },
  };
  const items = [1, 1, 1, 1, 1, 1, 1, 1, 1, 1].map((item) => (
    <HomeSectionCard />
  ));

  const [activeIndex, setActiveIndex] = useState(0);
  const carouselRef = useRef(null);

  const prevActive = () => {
    if (carouselRef.current) {
      carouselRef.current.slidePrev();
    }
  };
  const nextActive = () => {
    if (carouselRef.current) {
      carouselRef.current.slideNext();
    }
  };
  const syncAcitve = ({ item }) => {
    setActiveIndex(item);
  };

  return (
    <div className="relative   lg:px-4  ">
      <div className="relative p-4 border rounded-md">
        <AliceCarousel
          ref={carouselRef}
          mouseTracking
          items={items}
          controlsStrategy="alternate"
          infinite
          disableButtonsControls
          disableDotsControls
          // disableDotsControls
          responsive={responsive}
          onSlideChanged={syncAcitve}
          activeIndex={activeIndex}
        />

        {activeIndex !== items.length - 5 && (
          <Button
            className="z-50  "
            variant="contained"
            onClick={nextActive}
            sx={{
              position: "absolute",
              top: "8rem",
              right: "0rem",
              transform: "translateX(50%) rotate(90deg)",
              bgcolor: "white",
              "&:hover": {
                bgcolor: "white",
                opacity: 0.7,
              },
            }}
            aria-label="next"
          >
            <ArrowBackIosNewIcon
              sx={{ transform: "rotate(90deg)", color: "black" }}
            />
          </Button>
        )}
        {activeIndex !== 0 && (
          <Button
            onClick={prevActive}
            className="z-50  "
            variant="contained"
            sx={{
              position: "absolute",
              top: "8rem",
              left: "-4rem",
              transform: "translateX(50%) rotate(-90deg)",
              bgcolor: "white",
              "&:hover": {
                bgcolor: "white",
                opacity: 0.7,
              },
            }}
            aria-label="next"
          >
            <ArrowBackIosNewIcon
              sx={{ transform: "rotate(90deg)", color: "black" }}
            />
          </Button>
        )}
      </div>
    </div>
  );
}

export default HomeSectionCarosel;
