FROM node:14-slim
WORKDIR /usr/src
COPY ./package.json ./
COPY ./package-lock.json ./
RUN npm install
COPY . .
EXPOSE 3000
CMD [ "npm", "start" ]

#FROM node:16.15.0-alpine AS node
#COPY . /app
#WORKDIR /app
#
#ENV PATH /app/node_modules/.bin:$PATH
#
#COPY package.json ./
#COPY package-lock.json ./
#RUN npm install
#RUN npm install react-scripts@5.0.1 -g
#
#COPY . .
#EXPOSE 3000
##ako zatreba dodati nginx
#CMD ["npm", "start"]