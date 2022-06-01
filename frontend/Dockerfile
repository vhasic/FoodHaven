FROM node:14-slim
WORKDIR /frontend
COPY ./package.json .
COPY . /frontend
RUN npm install
EXPOSE 3000
CMD npm start
#CMD npm run build