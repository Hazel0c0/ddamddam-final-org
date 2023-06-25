import React, {useState, useEffect} from 'react';


const ProjectImage = ({projectIdx}) => {
  // console.log(`[ img.js ] P.idx : ${projectIdx}`)
  const [fileUrl, setFileUrl] = useState([]);

  const fileRequestURL = '//localhost:8181/api/ddamddam/project/load-s3';

  useEffect(() => {

    (async() => {
      const res = await fetch(
        `${fileRequestURL}?projectIdx=${projectIdx}`, {
          method: 'GET',
        });
      if (res.status === 200) {
        const imgUrl = await res.text();
        setFileUrl(imgUrl);
        // 이미지 수정
        // setFileUrl((prevUrls) => {
        //   const updatedUrls = [...prevUrls];
        //   updatedUrls[projectIdx] = imgUrl;
        //   return updatedUrls;
        // });
        setFileUrl(imgUrl);
        // console.log(`img (${projectIdx}): ${imgUrl}`);
      } else {
        const err = await res.text();
        console.log('img load error !! ' + err);

        // setFileUrl((prevUrls) => {
        //   const updatedUrls = [...prevUrls];
        //   updatedUrls[projectIdx] = null;
        //   return updatedUrls;
        // });
        setFileUrl(null);

      }
    })();

  }, []);

  return (
    <div className={'project-img'}>
      <img
        src={fileUrl}
        alt="Project Image"
        className={'project-img'}
        style={{
          height: 120,
          marginBottom: 20
        }}
      />
    </div>
  );
};

export default ProjectImage;