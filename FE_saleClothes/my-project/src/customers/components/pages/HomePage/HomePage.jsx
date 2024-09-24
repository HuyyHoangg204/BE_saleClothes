import React from 'react'
import MainCarosel from '../../HomeCarosel/MainCarosel'
import HomeSectionCarosel from '../../HomeSectionCarosel/HomeSectionCarosel'

function HomePage() {
  return (
    <div>
        <MainCarosel/>
        <div className='flex flex-col justify-center py-20 px-5'>
            <HomeSectionCarosel className=""/>
           
        </div>
    </div>
  )
}

export default HomePage